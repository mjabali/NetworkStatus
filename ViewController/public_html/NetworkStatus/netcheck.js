(function () {
    if (!window.application)
        window.application = {
        };

    /**
     * Method to check the network status
     */
    application.checkConnection = function () {
        var isConnected;
        var connectionType = navigator.network.connection.type;
        var states = {
        };
        states[Connection.UNKNOWN] = 'Unknown connection';
        states[Connection.ETHERNET] = 'Ethernet connection';
        states[Connection.WIFI] = 'WiFi connection';
        states[Connection.CELL_2G] = 'Cell 2G connection';
        states[Connection.CELL_3G] = 'Cell 3G connection';
        states[Connection.CELL_4G] = 'Cell 4G connection';
        states[Connection.NONE] = 'No network connection';
        
        if (connectionType == Connection.NONE || connectionType == Connection.UNKNOWN) {
            isConnected = false;
        }
        else {
            isConnected = true;
        }

        var obj = { connectionType : connectionType, isConnected : isConnected };
        
        return obj;
    }
})();