/**
 * 
 */
package ucm.fdi.ilsa.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fdi.ucm.server.interconect.model.DocumentCompleteJSON;
import fdi.ucm.server.interconect.model.GrammarJSON;
import fdi.ucm.server.interconect.model.StructureJSON;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class CompositeDocumentEditionDummy{


	private static final String ERROR_GRAMMAR = "Error Context can be applied to a grammar";
	private static final String ERROR_STRUCTURE = "Error Context can be applied because the structure not match";


	private String RandomIdVars;
	private int Heigh;
	private int Width;
	private CompositeDocumentEditionDummy Yo;
	private Long ContextId;
	private Panel PanelPrincipal;
	private DocumentCompleteJSON Documento;
	private StructureJSON SuperS;

	public CompositeDocumentEditionDummy(String randomIdVars, Long contextId, int Height, boolean Grammar) {
		RandomIdVars=randomIdVars;
		this.Heigh=Height-32;
		Width=Window.getClientWidth();
		
//		actualpage=0;
		
		int Variacion=75;
		
		if (Width>200)
			Width=Width-Variacion;
		
		ContextId=contextId;
		
		Yo=this;
//		HasMulti=false;
		
		Panel P=getContextDiv(RandomIdVars);
		P.clear();
		
		VerticalPanel VP = new VerticalPanel();
		VP.setSpacing(20);
		P.add(VP);
		
		PanelPrincipal=VP;
		

		Documento=getVariableBase(RandomIdVars);
		GWT.log(Documento.getDocumento().getId()+"");
		
		
		JSONObject DocumentoJ = setVariableBase2(Documento,RandomIdVars);

		JavaScriptObject DocumentoS = getVariableBaseJSONOBJ(RandomIdVars);
		
		String Mostar=DocumentoJ.toString();
		
		JSONObject JSOSucion = new JSONObject(DocumentoS);
		JSONValue JSOSucionV = JSOSucion.get(JSOSucion.keySet().iterator().next());
		if (JSOSucionV.isObject()!=null)
			JSOSucion=JSOSucionV.isObject();
		
		String Mostar2=JSOSucion.toString();
		
		DocumentCompleteJSON TMP=CreateJSONObject.create(JSOSucion);

		String Mostar3=CreateJSONObject.create(TMP).toString();
		
		Label T = new Label();
//		T.setSize(Width+"px", Heigh+"px");
		T.setText(Mostar);
		VP.add(T);
		
		Label T2 = new Label();
//		T.setSize(Width+"px", Heigh+"px");
		T2.setText(Mostar2);
		VP.add(T2);
		
		Label T3 = new Label();
//		T.setSize(Width+"px", Heigh+"px");
		T3.setText(Mostar3);
		VP.add(T3);
		
	
		
		
	}
	

	

	
	
	
	private StructureJSON gotContext(List<StructureJSON> listaS, Long contextId) {
		for (StructureJSON structterJSON : listaS) {
			if (structterJSON.getId().get(0).equals(contextId))
				return structterJSON;
			else
				{
				StructureJSON S=gotContext(structterJSON.getSons(),contextId);
				if (S!=null)
					return S;
				}
		}
		return null;
	}
	
	
	private List<StructureJSON> gotMultivaluedContext(List<StructureJSON> listaS, Long contextId) {
		List<StructureJSON> Hermanos=new ArrayList<StructureJSON>();
		for (StructureJSON structterJSON : listaS) {
			if (structterJSON.getId().get(0).equals(contextId))
				{
					for (StructureJSON structureJSON2 : listaS) {
						if (structureJSON2.getClaseOf().equals(SuperS.getClaseOf())&&(SuperS.getFather()==structureJSON2.getFather()||
								(SuperS.getFather()!=null&&structureJSON2.getFather()!=null&&SuperS.getFather().equals(structureJSON2.getFather()))))
							Hermanos.add(structureJSON2);
					}
					
					return Hermanos;
				}
			else
				{
				List<StructureJSON> S=gotMultivaluedContext(structterJSON.getSons(),contextId);
				if (S!=null)
					return S;
				}
		}
		return null;
	}



	private GrammarJSON gotContext(ArrayList<GrammarJSON> gramatica, Long contextId) {
		for (GrammarJSON grammarJSON : gramatica) {
			if (grammarJSON.getId().equals(contextId))
				return grammarJSON;
		}
		return null;
		
	}
	
	
	public static native Panel getContextDiv(String ContextID) /*-{

	$wnd.daletmp = '$wnd.dale = $wnd.VDocExpand'+ContextID;
	eval($wnd.daletmp)
	  return  $wnd.dale;

	}-*/;
	
	
	public static native DocumentCompleteJSON getVariableBase(String documentID2) /*-{
	$wnd.daletmp = '$wnd.dale = $wnd.DocExpand'+documentID2;
eval($wnd.daletmp)
  return  $wnd.dale;	  

}-*/;
	
	
	public static native JavaScriptObject getVariableBaseJSONOBJ(String documentID2) /*-{
	$wnd.daletmp = '$wnd.dale = $wnd.JDocExpand'+documentID2;
eval($wnd.daletmp)
  return  $wnd.dale;	  

}-*/;
	
	public static native JavaScriptObject getVariableBaseJObj(String documentID2) /*-{
	$wnd.daletmp = '$wnd.dale = $wnd.DocExpand'+documentID2;
eval($wnd.daletmp)
  return  $wnd.dale;	  

}-*/;

	public static String getIcon() {
		return "dummy.png";
		
	}

	public static native void setVariableBase(DocumentCompleteJSON DocumentoExpandido, String idrandomdoct) /*-{


	$wnd.tmp=DocumentoExpandido;
	$wnd.str = '$wnd.DocExpand'+idrandomdoct +' = $wnd.tmp';
	console.log($wnd.str);
	eval($wnd.str)

	}-*/;

//	@Override
	public void persistJS() {
		GWT.log(Documento.toString());
		setVariableBase(Documento,RandomIdVars);
		setVariableBase2(Documento,RandomIdVars);
		
	}
	
	
	private JSONObject setVariableBase2(DocumentCompleteJSON documento2, String randomIdVars2) {
		JSONObject DocumentoJ = CreateJSONObject.create(documento2);
//		JSONObject DocumentoJ = CreateJSONObject(documento2);
		setVariableBase3(DocumentoJ,randomIdVars2);
		return DocumentoJ;
		
	}



	public static native void setVariableBase3(JSONObject DocumentoExpandido, String idrandomdoct) /*-{


	$wnd.tmp=DocumentoExpandido;
	$wnd.str = '$wnd.JDocExpand'+idrandomdoct +' = $wnd.tmp';
	console.log($wnd.str);
	eval($wnd.str)

	}-*/;
















	public int getHeigh() {
		return Heigh;
	}
	


	protected boolean isMultivalued() {
		return false;
	}
	
	
	


//	@Override
	public boolean isWaitingUpdate() {
		return false;
	}


//	@Override
	public boolean updateContext() {
		return false;
	}


//	@Override
	public void setWaitingUpdate(boolean update) {
		
		
	}


	
}
