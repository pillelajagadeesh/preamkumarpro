appRaamApp.directive('noSpecialChar', function() {
return {
  require: 'ngModel',
  restrict: 'A',
  link: function(scope, element, attrs, modelCtrl) {
    modelCtrl.$parsers.push(function(inputValue) {
      if (inputValue == null)
        return ''
      cleanInputValue = inputValue.replace(/[^\w\s]/gi, '');
      if (cleanInputValue != inputValue) {
        modelCtrl.$setViewValue(cleanInputValue);
        modelCtrl.$render();
      }
      return cleanInputValue;
    });
  }
} });

/*appRaamApp
.directive("initFromForm", function ($parse) {
  return {
    link: function (scope, element, attrs) {
      var attr = attrs.initFromForm || attrs.ngModel || element.attrs('name'),
      val = attrs.value;
      if (attrs.type === "number") {val = parseInt(val)}
      $parse(attr).assign(scope, val);
    }
  };
});
*/