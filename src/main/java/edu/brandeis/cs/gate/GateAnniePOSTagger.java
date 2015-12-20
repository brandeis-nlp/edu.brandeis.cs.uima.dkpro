package edu.brandeis.cs.gate;

import edu.brandeis.cs.json.XmlToJson;
import edu.brandeis.cs.uima.UimaServiceException;
import org.lappsgrid.serialization.lif.Container;

/**
 * Created by shi on 12/20/15.
 */
public class GateAnniePOSTagger extends AbstractGateAnnieService {


    String dsl = null;

    public GateAnniePOSTagger(){
        dsl = getTemplate();
    }

    @Override
    public String execute(Container json) throws UimaServiceException {
        String txt = json.getText();
        try {
            String xml = getXML(txt);
            return XmlToJson.transform(xml, dsl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UimaServiceException(e.getMessage());
        }
    }
}
