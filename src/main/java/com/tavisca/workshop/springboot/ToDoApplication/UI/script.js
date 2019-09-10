let arr = [];

window.onload = function ()
      {
        fetch('http://localhost:8080/api/v1/getAllTasks')
          .then(response => response.json())
          .then(function(json){
          for(let i=0; i<json.taskList.length; i++){
          arr.push(json.taskList[i]);
          }
          DisplayTable(arr);
          })
      }

function changeTab(event, tabName) {
  let i, tabcontent, list_item;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  list_item = document.getElementsByClassName("list-item");
  for (i = 0; i < list_item.length; i++) {
    list_item[i].className = list_item[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  event.currentTarget.className += " active";
}

function searchItem()
      {
        let input, filter,j=0;
        let searchresult = [];
        input = document.getElementById("search-bar-id");
        filter = input.value;

//        for(let i=0;i<arr.length;i++)
//        {
//          if(arr[i].includes(filter))
//          {
//            searchresult[j] = arr[i];
//            j++;
//          }
//        }

    fetch('http://localhost:8080/api/v1/searchTask/'+filter)
          .then(response => response.json())
          .then(function(json){
          searchresult.push(json.taskList);
          DisplayTable(searchresult);
          })

        input.value="";
      }

function DisplayTable(array)
      {
        let table = document.getElementById("task-table");
        table.innerHTML='';
        for(let i=0;i<array.length;i++)
        {
           let row = table.insertRow(i);
           let cell1 = row.insertCell(0);
           let cell2 = row.insertCell(1);
           let cell3 = row.insertCell(2);
           let ebtn = "edit-button";
           let rbtn = "remove-button";
           let editOnClick="editItem(this)";
           let deleteOnClick ="deleteItem(this)";
           cell2.innerHTML =`<button class=${ebtn} onclick=${editOnClick}></button>`;
           cell3.innerHTML =`<button class=${rbtn} onclick=${deleteOnClick}></button>`;
           cell1.innerHTML = array[i];

        }
      }

      function deleteItem(element)
      {
        let row = element.parentNode.parentNode;
        let rowContent = row.firstChild.innerHTML;
        let i = row.rowIndex;
        let index = arr.indexOf(rowContent);
        if (index > -1)
        {
          arr.splice(index, 1);
        }
       fetch('http://localhost:8080/api/v1/deleteTask/'+i, {
          method: 'DELETE'
        })
       DisplayTable(arr);
      }

      function addItem()
      {
        let input, filter;
        input = document.getElementById("search-bar-id");
        filter = input.value;
        let object={"taskList" : filter};
        let brr = [];

        if(!arr.includes(filter) && filter.length!=0)
        {
            fetch('http://localhost:8080/api/v1/addTask', {
                    method: 'POST',
                    body: JSON.stringify(object),
                    headers: {
                      "Content-type": "application/json; charset=UTF-8"
                    }
                  })
            .then(response => response.json())
            .then(function(json){
                   for(let i=0; i<json.taskList.length; i++){
                   brr.push(json.taskList[i]);
                   }
                   DisplayTable(brr);
                   })
        }
        else
        {
        alert("Duplicate or null entry!!");
        }

        input.value="";
      }

      function editItem(element)
      {
        let row = element.parentNode.parentNode;
        let rowcontent = row.firstChild.innerHTML;
        let ubtn = "update-button";
        let index = arr.indexOf(rowcontent);
        row.innerHTML=`<td><input type='text' id='edit' value=${rowcontent}></td><td><button class='${ubtn}' onclick=updateItem(this,${index})></button></td> `;
      }
      function updateItem(element,index)
      {
        let rowcontent=element.parentNode.parentNode.firstChild.firstChild.value;
        let object={"taskList" : rowcontent};
        if(!arr.includes(rowcontent) && rowcontent.length!=0)
        {
          //arr[index] = rowcontent;
          let crr = [];
          fetch('http://localhost:8080/api/v1/editTask/'+index, {
                              method: 'PUT',
                              body: JSON.stringify(object),
                              headers: {
                                "Content-type": "application/json; charset=UTF-8"
                              }
                            })
                      .then(response => response.json())
                      .then(function(json){
                             for(let i=0; i<json.taskList.length; i++){
                             crr.push(json.taskList[i]);
                             }
                             DisplayTable(crr);
                             })

        }
        else
        {
            alert("Duplicate or null entry!!");
        }
      }