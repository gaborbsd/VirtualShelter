(function () {

    angular.module('vshelterApp')
        .directive('fileUpload', FileUploadDirective);

    function FileUploadDirective() {
        return {
            restrict: 'A',
            scope: true,
            link: function (scope, el, attrs) {
                el.bind('change', function (event) {
                    var files = event.target.files;
                    for (var i = 0; i < files.length; i++) {
                        scope.$emit("fileSelected", {
                            file: files[i]
                        });
                    }
                });
            }
        };
    }

})();