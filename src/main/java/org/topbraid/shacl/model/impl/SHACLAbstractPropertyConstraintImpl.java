package org.topbraid.shacl.model.impl;

import org.topbraid.shacl.model.SHACLAbstractPropertyConstraint;
import org.topbraid.shacl.vocabulary.SHACL;
import org.topbraid.spin.util.JenaUtil;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * Default implementation of SHACLAbstractPropertyConstraint.
 * 
 * @author Holger Knublauch
 */
public abstract class SHACLAbstractPropertyConstraintImpl extends SHACLTemplateCallImpl implements SHACLAbstractPropertyConstraint {
	
	public SHACLAbstractPropertyConstraintImpl(Node node, EnhGraph graph) {
		super(node, graph);
	}
	
	
	@Override
	public String getComment() {
		return JenaUtil.getStringProperty(this, RDFS.comment);
	}


	@Override
	public Property getPredicate() {
		Resource r = JenaUtil.getResourceProperty(this, SHACL.predicate);
		if(r != null && r.isURIResource()) {
			return new PropertyImpl(r.asNode(), (EnhGraph)r.getModel());
		}
		else {
			return null;
		}
	}

	
	@Override
	public Resource getValueType() {
		return JenaUtil.getResourceProperty(this, SHACL.valueType);
	}


	@Override
	public String getVarName() {
		Property argProperty = getPredicate();
		if(argProperty != null) {
			return argProperty.getLocalName();
		}
		else {
			return null;
		}
	}
}