function loadContent() {
  var pageSize = document.getElementById("limit").value;
  $.get("//localhost:8080/rest/tasks?pageNumber=" + 0 + "&pageSize=" + pageSize, function (data) {
    buildHtmlTable(data);
  });
  $.get("//localhost:8080/rest/tasks/count", function (data) {
    createPaging(pageSize, data, 1);
  });
}


function buildHtmlTable(data) {
  var selector = '#excelDataTable';
  var Table = document.getElementById("excelDataTable");
  Table.innerHTML = "";
  var columns = addAllColumnHeaders(data, selector);

  for (var i = 0; i < data.length; i++) {
    var row$ = $('<tr/>');
    for (var colIndex = 0; colIndex < columns.length; colIndex++) {
      var cellValue = data[i][columns[colIndex]];
      if (cellValue == null) cellValue = "";
      row$.append($('<td/>').html(cellValue));
    }
    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("class", "btn btn-success btn-sm");
    deleteButton.value = data[i].id;
    deleteButton.addEventListener("click", function () {
      deleteTask(deleteButton.value);
      console.log(deleteButton.value)
    });
    deleteButton.appendChild(document.createTextNode("DELETE"));
    row$.append(deleteButton);
    $(selector).append(row$);
  }
}

function deleteTask(taskId) {
  $.post("//localhost:8080/rest/tasks/delete/" + taskId, function () {
    loadContent();
  });
}

// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records.
function addAllColumnHeaders(myList, selector) {
  var columnSet = [];
  var headerTr$ = $('<tr/>');

  for (var i = 0; i < myList.length; i++) {
    var rowHash = myList[i];
    for (var key in rowHash) {
      if ($.inArray(key, columnSet) === -1) {
        columnSet.push(key);
        headerTr$.append($('<th/>').html(key));
      }
    }
  }
  headerTr$.append($('<th/>').html("Delete"));
  $(selector).append(headerTr$);

  return columnSet;
}

function createPaging(playersInPage, playersSummary, currentPage) {
  let paggingBar = document.getElementById("pagging-bar");
  paggingBar.innerHTML = "";
  let pagesCount = playersSummary / playersInPage;
  if (pagesCount > 1) {

    for (let i = 1; i < pagesCount+1; i++) {
      let li = document.createElement("li");
      if (i === currentPage) {
        li.setAttribute("class", "page-item disabled");
      } else {
        li.setAttribute("class", "page-item");
      }
      let a = document.createElement("a");
      a.setAttribute("class", "page-link");
      a.setAttribute("href", "#");
      let root = document.getElementById("root").getAttribute("about");
      a.setAttribute("onclick", "updateTable(" + (i-1) + ")");
      a.appendChild(document.createTextNode(i));
      li.appendChild(a);
      paggingBar.appendChild(li);
    }
  }
}

function createPagesButton(playersInPage, playersSummary, currentPage) {

}

function updateTable(pageNumber) {
  var pageSize = document.getElementById("limit").value;
  $.get("//localhost:8080/rest/tasks?pageNumber=" + pageNumber + "&pageSize=" + pageSize, function (data) {
    buildHtmlTable(data);
  });
  $.get("//localhost:8080/rest/tasks/count", function (data) {
    createPaging(pageSize, data, pageNumber+1);
  });
}