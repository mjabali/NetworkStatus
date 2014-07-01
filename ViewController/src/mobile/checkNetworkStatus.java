package mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class checkNetworkStatus {
    
    private String connectionType;
    private boolean isConnected;
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        
    public checkNetworkStatus() {
    }

    public void checkNetworkStatusAction(ActionEvent actionEvent) {
        String networkDetails = (String)AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                                                  "application.checkConnection",
                                                                                                  new Object[] { }); 
            try { 
                JSONObject obj = new JSONObject(networkDetails); 
                String connectionType = (String)obj.getString("connectionType"); 
                boolean isConnected = (boolean)obj.getBoolean("isConnected"); 
                setIsConnected(isConnected); 
                setConnectionType(connectionType); 
            } catch (JSONException e) { 
                throw new AdfException("Error while trying to connect to network", AdfException.ERROR); 
            } 
    }
        public void addPropertyChangeListener(PropertyChangeListener l) {
            propertyChangeSupport.addPropertyChangeListener(l);
        }

        public void removePropertyChangeListener(PropertyChangeListener l) {
            propertyChangeSupport.removePropertyChangeListener(l);
        }

        public void setIsConnected(boolean isConnected) {
            boolean oldIsConnected = this.isConnected;
            this.isConnected = isConnected;
            propertyChangeSupport.firePropertyChange("isConnected", oldIsConnected, isConnected);
        }

        public boolean isIsConnected() {
            return isConnected;
        }

        public boolean getIsConnected() {
            return isConnected;
        }

        public void setConnectionType(String connectionType) {
            String oldConnectionType = this.connectionType;
            this.connectionType = connectionType;
            propertyChangeSupport.firePropertyChange("connectionType", oldConnectionType, connectionType);
        }

        public String getConnectionType() {
            return connectionType;
        }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport, propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}
