package com.smt.web.client.toolBox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProgressWindow extends Window{
    
    private static final long serialVersionUID = 161901606322111895L;
    
    public ProgressWindow(){
    	String title="Please Wait";
    	String message="Loading";
    	ProgressBar 	progressBar = new ProgressBar();
    	progressBar.setIndeterminate(true);
    	progressBar.setEnabled(true);
    	progressBar.setValue(0);
    	
    	
        setCaption(title);
        
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.setSizeFull();
        
        setModal(true);
        setClosable(false);
        this.setResizable(false);
        
        Label lblTitle = new Label(message);
        root.addComponent(lblTitle);
        root.addComponent(progressBar);
     
        root.setComponentAlignment(progressBar, Alignment.MIDDLE_CENTER);
        root.setComponentAlignment(lblTitle, Alignment.MIDDLE_CENTER);
        setContent(root);
        setWidth(300, Unit.PIXELS);
        setHeight(100, Unit.PIXELS);
    }

    public void show() {
        UI.getCurrent().addWindow(this);
    }
    
    public void close(){
        UI.getCurrent().removeWindow(this);  
    }
    
}