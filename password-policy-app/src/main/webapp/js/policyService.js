/**
 * Rest service to post to Queue
 */
angular.module('app').factory('policyService', policyService);
function policyService($resource) {
    var service = {};
    service.register = function(passwordPolicy, success, failure) {
        var Policy = $resource("/password-policy-app/v1/policy/password");
        Policy.save({}, passwordPolicy, success, failure);
    };
    return service;
}
