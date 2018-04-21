var webBook = {
    url : {
        saveUrl : "/"
    },
    show : function () {
        $("#editWebBookModal").modal("show");
      },
    hide : function () {
        $("#editWebBookModal").modal("hide");
    },
    save : function () {
        var wb = {
            name : $("#name").val(),
            showUrl : $("#showUrl").val(),
            replaceStr : $("#replaceStr").val()
        }
        $.ajax({
           url : ""
        });
    }
};