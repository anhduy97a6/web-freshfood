// function myFunction() {
//   // Declare variables 
//   var input, filter, table, tr, td, i;
//   input = document.getElementById("myInput");
//   filter = input.value.toUpperCase();
//   table = document.getElementById("myTable");
//   tr = table.getElementsByTagName("tr");

//   // Loop through all table rows, and hide those who don't match the search query
//   for (i = 0; i < tr.length; i++) {
//     td = tr[i].getElementsByTagName("td")[0];
//     if (td) {
//       if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
//         tr[i].style.display = "";
//       } else {
//         tr[i].style.display = "none";
//       }
//     } 
//   }
// }
$(document).ready(function() {
  $('#myInput').on('keyup', function(event) {
    event.preventDefault();
    /* Act on the event */
    var tukhoa = $(this).val().toLowerCase();
    $('#dataTable tr').filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(tukhoa)>-1);
    });

  });
});