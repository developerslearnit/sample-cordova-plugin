var exec = require("cordova/exec");

module.exports.set = function(arg0, arg1, success, error) {
  exec(success, error, "samplecordovapreference", "set", [arg0, arg1]);
};

module.exports.get = function(arg0, success, error) {
  exec(success, error, "samplecordovapreference", "get", [arg0]);
};
