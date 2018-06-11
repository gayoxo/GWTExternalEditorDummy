package ucm.fdi.ilsa.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTExternalDummy implements EntryPoint {
	
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
    	
    }-*/;

    public static void setContext(String IdVentana,String contextId,String Height,boolean isgrammar,boolean edit,boolean views,boolean CompleteView) {
//		try {
			Long contLong=Long.parseLong(contextId);
			Integer heiInteger=Integer.parseInt(Height);
			if (edit)
				new CompositeDocumentEditionDummy(IdVentana, contLong, heiInteger, isgrammar);
			else
				new CompositeDocumentDescriptionDummy(IdVentana, contLong, heiInteger, CompleteView, isgrammar, views);
//		} catch (Exception e) {
//			Window.alert(e.getMessage());
//			Window.
//			e.printStackTrace();
//		}
		
		
	}
    
    public static String getIcon() {
    	return CompositeDocumentDescriptionDummy.getIcon();
		
		
	}


	@Override
	public void onModuleLoad() {
		GWT.log("Dummy Load");
		
	}
}
