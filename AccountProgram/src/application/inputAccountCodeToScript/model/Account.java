package application.inputAccountCodeToScript.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Account {

    private final IntegerProperty accountCode;
    private final StringProperty accountName;
    private final StringProperty scriptTxt;

    public Account() {
        this(0, null, null);
    }

    public Account(int code, String name, String scritp) {
        this.accountCode = new SimpleIntegerProperty(code);
        this.accountName = new SimpleStringProperty(name);
        this.scriptTxt = new SimpleStringProperty(scritp);       
       
    }

    public int getAccountCode() {
        return accountCode.get();
    }

    public void setAccountCode(int code) {
        this.accountCode.set(code);
    }

    public IntegerProperty AccountCodeProperty() {
        return accountCode;
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String name) {
        this.accountName.set(name);
    }

    public StringProperty AccountNameProperty() {
        return accountName;
    }

    public String getScript() {
        return scriptTxt.get();
    }

    public void setScript(String scritp) {
        this.scriptTxt.set(scritp);
    }

    public StringProperty ScriptProperty() {
        return scriptTxt;
    }    
}