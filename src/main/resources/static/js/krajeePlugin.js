/**
 * Created by qurbonov on 10/29/2015.
 */
$(document).on('ready', function () {
    $("#uploadFile").fileinput({
        previewFileType: "text",
//                allowedFileExtensions: ["txt", "md", "ini", "text"],
        previewClass: "bg-warning"
    });
});