package jaist.holab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


import com.github.owlcs.ontapi.jena.OntModelFactory;
import com.github.owlcs.ontapi.jena.model.OntClass;
import com.github.owlcs.ontapi.jena.model.OntDataProperty;
import com.github.owlcs.ontapi.jena.model.OntIndividual;
import com.github.owlcs.ontapi.jena.model.OntModel;
import com.github.owlcs.ontapi.jena.model.OntObjectProperty;
import com.github.owlcs.ontapi.jena.vocabulary.XSD;


/**
 * Hello world!
 *
 */
public class App 
{
	public static final String NS  = Const.NS + "#";
	public static OntModel model = null;
    public static void main( String[] args )
    {
        System.out.println( "Started" );
        model = OntModelFactory.createModel();
        model.setNsPrefix("tactile", Const.NS);
        OntClass skinOnt = model.createOntClass(NS + "SkinBasedTactile");
        		 skinOnt.addSuperClass(model.createOntClass(NS + "Tactile"));
        		 skinOnt.addSuperClass(statePP());
        		 skinOnt.addSuperClass(skinTypePP());
        		 skinOnt.addSuperClass(geometryPP());
        		 skinOnt.addSuperClass(weightPP());
        		 skinOnt.addSuperClass(volumePP());
        		 skinOnt.addSuperClass(skinTexturePP());
        		 skinOnt.addSuperClass(colorPP());
        		 skinOnt.addSuperClass(markerPP());
        		 skinOnt.addSuperClass(materialPP());
        		 skinOnt.addSuperClass(touchInforPP());
        		
        FileWriter fw = null;
		try {
			fw = new FileWriter("tactile_V1.ttl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.write(fw, "TTL");
		System.out.println( "Finished" );
    }
    public static OntClass geometryPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasSkinGeometry");
    	return model.createObjectAllValuesFrom(pp, geometryCls());
    	
    }
    public static OntClass markerPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasMarker");
    	return model.createObjectAllValuesFrom(pp, markerCls());
    	
    }
    public static OntClass materialPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasSkinMaterial");
    	return model.createObjectAllValuesFrom(pp, materialCls());
    	
    }
    public static OntClass touchInforPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasTouchInformation");
    	return model.createObjectAllValuesFrom(pp, touchInformationCls());
    	
    }
    public static OntClass touchInformationCls() {
    	OntClass cls = model.createOntClass(NS + "TouchInformation");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasAppliedForce"), 
    					 appliedForceCls()));	 
    			cls.addSuperClass(model.createObjectAllValuesFrom(
   					 model.createObjectProperty(NS + "hasVelocity"), 
   					 velocityCls()));	
    			cls.addSuperClass(model.createObjectAllValuesFrom(
      					 model.createObjectProperty(NS + "hasAcceleration"), 
      					 accelerationCls()));	
    	return cls;
    }
    public static OntClass appliedForceCls() {
    	OntClass cls = model.createOntClass(NS + "AppliedForce");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasForceType"), 
    					 forceTypeValue()));
    			cls.addSuperClass(directionPP());
    			cls.addSuperClass(magnitudePP());
    			cls.addSuperClass(positionPP());
    	return cls;
    }
    public static OntClass velocityCls() {
    	OntClass cls = model.createOntClass(NS + "Velocity");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(directionPP());
    			cls.addSuperClass(magnitudePP());
    			cls.addSuperClass(positionPP());
    	return cls;
    }
    public static OntClass accelerationCls() {
    	OntClass cls = model.createOntClass(NS + "Acceleration");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(directionPP());
    			cls.addSuperClass(magnitudePP());
    			cls.addSuperClass(positionPP());
    	return cls;
    }
    public static OntClass magnitudePP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasMagnitude");
    	return model.createObjectAllValuesFrom(pp, measurementClass());
    }
    public static OntClass directionPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasDirection");
    	return model.createObjectAllValuesFrom(pp, measurementClass());
    }
    public static OntClass positionPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasAffectedMarkerPosition");
    	return model.createObjectAllValuesFrom(pp, markerCls());
    }
    public static OntClass materialCls() {
    	OntClass cls = model.createOntClass(NS + "SkinMaterial");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createDataAllValuesFrom(
    					 model.createDataProperty(NS + "hasType"), 
    					 model.getDatatype(XSD.xstring)));
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasMaterialPhysicalProperty"), 
    					 materialPhysicalCls()));	 
    			cls.addSuperClass(model.createObjectAllValuesFrom(
   					 model.createObjectProperty(NS + "hasStiffnessMatrice"), 
   					 measurementClass()));	
    	return cls;
    }
    public static OntClass materialPhysicalCls() {
    	OntClass cls = model.createOntClass(NS + "MaterialPhysicalProperty");
    			cls.addSuperClass(model.getOWLNothing());

    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasPoisonRatio"), 
    					 measurementClass()));	 
    			cls.addSuperClass(model.createObjectAllValuesFrom(
   					 model.createObjectProperty(NS + "hasShearModulus"), 
   					 measurementClass()));	
    			cls.addSuperClass(model.createObjectAllValuesFrom(
      					 model.createObjectProperty(NS + "hasYoungModulus"), 
      					 measurementClass()));	
    	return cls;
    }
    public static OntClass markerCls() {
    	OntClass cls = model.createOntClass(NS + "Marker");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createDataMinCardinality(
    					 model.createDataProperty(NS + "hasID"),1, 
    					 model.getDatatype(XSD.xstring)));
    			cls.addSuperClass(colorPP());
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasCoordinate"), 
    					 measurementClass()));	 
    			cls.addSuperClass(model.createObjectAllValuesFrom(
   					 model.createObjectProperty(NS + "hasDeformation"), 
   					 measurementClass()));	
    			cls.addSuperClass(model.createObjectAllValuesFrom(
      					 model.createObjectProperty(NS + "hasNeighborMarker"), 
      					 model.getOntClass(NS + "Marker")));
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "isLocatedAt"), skinLocationValue()));
    	return cls;
    }
    public static OntClass geometryCls() {
    	OntClass cls = model.createOntClass(NS + "SkinGeometry");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createDataAllValuesFrom(
    					 model.createDataProperty(NS + "hasShape"), 
    					 model.getDatatype(XSD.xstring)));
    			cls.addSuperClass(model.createObjectAllValuesFrom(
    					 model.createObjectProperty(NS + "hasThickness"), 
    					 measurementClass()));	 
    			cls.addSuperClass(model.createObjectAllValuesFrom(
   					 model.createObjectProperty(NS + "hasDimention"), 
   					 measurementClass()));	
    			cls.addSuperClass(model.createObjectAllValuesFrom(
      					 model.createObjectProperty(NS + "hasDimention"), 
      					 measurementClass()));	
    	return cls;
    }
    public static OntClass measurementClass() {
    	OntClass cls = model.createOntClass(NS + "Measurement");
    			cls.addSuperClass(model.getOWLNothing());
    			cls.addSuperClass(model.createDataAllValuesFrom(
    					 model.createDataProperty(NS + "hasUnit"), 
    					 model.getDatatype(XSD.xstring)));
    			cls.addSuperClass(model.createDataAllValuesFrom(
   					 model.createDataProperty(NS + "hasTimeStamp"), 
   					 model.getDatatype(XSD.dateTimeStamp)));
    			cls.addSuperClass(model.createDataMinCardinality(
      					 model.createDataProperty(NS + "hasValue"),1, 
      					 model.getDatatype(XSD.xfloat)));
    			cls.addSuperClass(model.createDataAllValuesFrom(
      					 model.createDataProperty(NS + "hasAccuracy"), 
      					 model.getDatatype(XSD.xfloat)));
    			 
    	return cls;
    }
    public static OntClass skinTypePP() {
    	OntDataProperty pp = model.createDataProperty(NS +"hasSkinType");
    	return model.createDataAllValuesFrom(pp, model.getDatatype(XSD.xstring));
    }
    public static OntClass colorPP() {
    	OntDataProperty pp = model.createDataProperty(NS +"hasColor");
    	return model.createDataAllValuesFrom(pp, model.getDatatype(XSD.xstring));
    }
    public static OntClass skinTexturePP() {
    	OntDataProperty pp = model.createDataProperty(NS +"hasSkinTexture");
    	return model.createDataAllValuesFrom(pp, model.getDatatype(XSD.xboolean));
    }
    public static OntClass weightPP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasWeight");
    	return model.createObjectAllValuesFrom(pp, measurementClass());
    }
    public static OntClass volumePP() {
    	OntObjectProperty pp = model.createObjectProperty(NS +"hasVolume");
    	return model.createObjectAllValuesFrom(pp, measurementClass());
    }
    
    public static OntClass statePP() {
    	OntObjectProperty statePP = model.createObjectProperty(NS +"hasTouchingState");
    	return model.createObjectMinCardinality(statePP, 1, statePPValue());
    }
    public static OntClass statePPValue() {
    	Collection<OntIndividual > individuals = new ArrayList<OntIndividual>();
    	individuals.add(model.createIndividual(NS + "Touching"));
    	individuals.add(model.createIndividual(NS + "NoTouching"));
    	OntClass.OneOf states = model.createObjectOneOf(individuals);
    	return states;
    	
    }   
    public static OntClass skinLocationValue() {
    	Collection<OntIndividual > individuals = new ArrayList<OntIndividual>();
    	individuals.add(model.createIndividual(NS + "OutsideOfSkin"));
    	individuals.add(model.createIndividual(NS + "InsideOfSkin"));
    	OntClass.OneOf states = model.createObjectOneOf(individuals);
    	return states;
    	
    }  
    public static OntClass forceTypeValue() {
    	Collection<OntIndividual > individuals = new ArrayList<OntIndividual>();
    	individuals.add(model.createIndividual(NS + "Concentration"));
    	individuals.add(model.createIndividual(NS + "Distribution"));
    	OntClass.OneOf states = model.createObjectOneOf(individuals);
    	return states;
    	
    } 
}
