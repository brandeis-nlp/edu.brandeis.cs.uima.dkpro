package edu.brandeis.cs.uima.dkpro;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import edu.brandeis.cs.json.XmlToJson;
import edu.brandeis.cs.uima.ServiceException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.lappsgrid.serialization.lif.Container;


public class StanfordNlpNamedEntityRecognizer extends AbstractDkProOpenNlpService {


    static AnalysisEngine aae;

    static {
        try {
            aae = uimaDkProInit(StanfordNamedEntityRecognizer.class);
            System.out.println("StanfordNamedEntityRecognizer Init...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String dsl = null;

    public StanfordNlpNamedEntityRecognizer(){
        dsl = getTemplate();
    }

    @Override
    public String execute(Container json) throws ServiceException {
        String txt = json.getText();
        try {
            String xml = uimaDkProXml(aae, txt);
            return XmlToJson.transform(xml, dsl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
