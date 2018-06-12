package ucm.fdi.ilsa.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTExternalDummy implements EntryPoint {
	
	private static CompositeDocumentEditionDummy Actual;


	static {
        export();
    }
	
	public GWTExternalDummy() {
		
	}
	
	
	/**
     * Makes our setData method accessible from plain JS
     */
    private static native void export() /*-{
    	
    	$wnd.DummySetContext = @ucm.fdi.ilsa.client.GWTExternalDummy::setContext(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZ)
    	$wnd.DummyGetIcon = @ucm.fdi.ilsa.client.GWTExternalDummy::getIcon()
    	$wnd.DummyPersist = @ucm.fdi.ilsa.client.GWTExternalDummy::getPersist()
    	
    }-*/;

    public static void setContext(String IdVentana,String contextId,String Height,boolean isgrammar,boolean edit,boolean views,boolean CompleteView) {
//		try {
			Long contLong=Long.parseLong(contextId);
			Integer heiInteger=Integer.parseInt(Height);
			if (edit)
				Actual=new CompositeDocumentEditionDummy(IdVentana, contLong, heiInteger, isgrammar);
			else
				Actual=new CompositeDocumentDescriptionDummy(IdVentana, contLong, heiInteger, CompleteView, isgrammar, views);
//		} catch (Exception e) {
//			Window.alert(e.getMessage());
//			Window.
//			e.printStackTrace();
//		}
		
		
	}
    
    public static String getIcon() {
    	return CompositeDocumentDescriptionDummy.getIcon();
		
		
	}
    
    public static void getPersist() {
    	if (Actual!=null)
    		Actual.persistJS();
		
		
	}


	@Override
	public void onModuleLoad() {
		GWT.log("Dummy Load");
		
	}
}
