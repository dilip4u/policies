angular.module('app').controller("PolicyController", MainController);
function MainController(policyService, $scope) {
    var vm = this;
    vm.showConfirm = true;
    vm.title = 'Password Policy Administration';
    vm.passwordPolicy = {};
    vm.createPolicyRequest = createPolicyRequest;
    // to show confirmation
    $scope.showSuccessAlert = false;
    // switch flag
    $scope.switchBool = switchBool;
    function switchBool(value) {
        $scope[value] = !$scope[value];
    }

    function createPolicyRequest() {
        console.log('begin queueing');
        policyService.register(vm.passwordPolicy, 
                    function(returnedData) {//success handle
                        console.log(vm.passwordPolicy);
                        console.log(returnedData);
                        vm.showConfirm = false;
                        vm.passwordPolicy = {};
                        $scope.showSuccessAlert = true;
            
                    }, function() {//failure handle
                        alert("Exception has occured while Queueing. Please contact Techworks. Checking logs might be helpful!");
                    });
    }
}

