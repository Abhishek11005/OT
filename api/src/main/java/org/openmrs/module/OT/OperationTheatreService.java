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

package org.openmrs.module.OT;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(OperationTheatreService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface OperationTheatreService extends OpenmrsService {
     
	/*
	 * Add service methods here
	 * 
	 */
	
	/**
	 * Return all minor OT procedures concepts
	 * 
	 * @return List<Concept>
	 **/
	public List<Concept> getProceduresMinorOT();
	
	/**
	 * Find minorOT schedules
	 * 
	 * @param startDate
	 * @param phrase
	 * @param procedures
	 * @param page
	 * @return List<OpdTestOrder> 
	 * @throws ParseException
	 */
	public List<OpdTestOrder> getSchedulesMinorOT(Date startDate, String phrase,
			List<Concept> procedures, int page) throws ParseException;
	
	/**
	 * Count Minor OT schedules
	 * 
	 * @param startDate
	 * @param phrase
	 * @param procedures
	 * @return Integer
	 * @throws ParseException
	 */
	public Integer countScheduleMinorOT(Date startDate, String phrase, 
			List<Concept> procedures) throws ParseException;

	/**
	 * Returns a diagnosis related to OT procedure
	 * 
	 * @param encounterId
	 * @return String
	 */
	public Obs getDiagnosisOTProcedure(Encounter encounterId);
}