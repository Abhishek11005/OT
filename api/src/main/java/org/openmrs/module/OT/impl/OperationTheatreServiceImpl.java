/**
 *  Copyright 2013 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of OT module.
 *
 *  OT module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  OT module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OT module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.OT.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.OT.OperationTheatreService;
import org.openmrs.module.OT.db.OperationTheatreDAO;
import org.openmrs.module.OT.util.OTConstants;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;

/**
 * It is a default implementation of {@link OperationTheatreService}.
 */
public class OperationTheatreServiceImpl extends BaseOpenmrsService implements OperationTheatreService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	public OperationTheatreServiceImpl() {
	}
	
	private OperationTheatreDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(OperationTheatreDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public OperationTheatreDAO getDao() {
	    return dao;
    }
	
	public List<Concept> getProceduresMinorOT() {
		Concept concept = Context.getConceptService().getConcept(OTConstants.CONCEPT_CLASS_NAME_MINOR_OT);
		
		Collection<ConceptAnswer> allMinorOTProcedures = null;
		List<Concept> id = new ArrayList<Concept>();
		if( concept != null )
		{
			allMinorOTProcedures = concept.getAnswers();
			for (ConceptAnswer c: allMinorOTProcedures){
				id.add(c.getAnswerConcept());
			}
			return id;
		}
		return null;
	}

	public List<OpdTestOrder> getSchedulesMinorOT(Date startDate, String phrase,
			List<Concept> procedures, int page) throws ParseException {
		
		List<Patient> patients = Context.getPatientService()
				.getPatients(phrase);
		List<OpdTestOrder> schedules = dao.getSchedulesMinorOT(startDate, 
				procedures, patients, page);
		return schedules;
	}
	
	public Integer countScheduleMinorOT(Date startDate, String phrase, 
			List<Concept> procedures) throws ParseException {
		
		List<Patient> patients = Context.getPatientService()
				.getPatients(phrase);
		return dao.countScheduleMinorOT(startDate, procedures, patients);
	}

	public Obs getDiagnosisOTProcedure(Encounter encounter) {
		Concept concept = Context.getConceptService()
				.getConcept(OTConstants.CONCEPT_CLASS_NAME_DIAGNOSIS);
		return dao.getDiagnosisOTProcedure(encounter, concept);
	}
}