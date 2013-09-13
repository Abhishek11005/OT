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

package org.openmrs.module.OT.db;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.module.OT.OperationTheatreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;

/**
 *  Database methods for {@link OperationTheatreService}.
 */
public interface OperationTheatreDAO {
	
	/*
	 * Add DAO methods here
	 */
	
	/**
	 * 
	 * @param startDate
	 * @param procedures
	 * @param patients
	 * @param page
	 * @return List<OpdTestOrder>
	 * @throws ParseException
	 */
	public List<OpdTestOrder> getSchedulesMinorOT(Date scheduleDate, List<Concept> procedures,
			List<Patient> patients, int page) throws ParseException;
	
	/**
	 * 
	 * @param scheduleDate
	 * @param procedures
	 * @param patients
	 * @return Integer
	 * @throws ParseException
	 */
	public Integer countScheduleMinorOT(Date scheduleDate, List<Concept> procedures,
			List<Patient> patients) throws ParseException;
	
	/**
	 * 
	 * @param encounterId
	 * @param concept
	 * @return Obs
	 */
	public Obs getDiagnosisOTProcedure(Encounter encounter, Concept concept);
}