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

package org.openmrs.module.OT.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.OT.OperationTheatreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class OperationTheatreUtil {

	/**
	 * Generate a list of OT Schedule Model using schedules
	 * 
	 * @param schedules
	 * @param otProcedureTreeMap
	 * @return List<OTscheduleModel>
	 */
	public static List<OTScheduleModel> generateModelsFromSchedules(
			List<OpdTestOrder> schedules) {
		
		List<OTScheduleModel> models = new ArrayList<OTScheduleModel>();
		for (OpdTestOrder schedule : schedules) {
			OTScheduleModel osm = generateModel(schedule);
			models.add(osm);
		}
		return models;
	}
	
	/**
	 * Generate a single OT schedule model
	 * 
	 * @param schedule
	 * @param otProcedureTreeMap
	 * @return
	 */
	private static OTScheduleModel generateModel(OpdTestOrder schedule) {
	
		Encounter encounter = schedule.getEncounter();
		Obs pDiagnosis;
		OTScheduleModel osm = new OTScheduleModel();
		OperationTheatreService ots = (OperationTheatreService) Context
				.getService(OperationTheatreService.class);
		if (encounter != null && encounter.getAllObs().size() != 0) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			osm.setStartDate(sdf.format(schedule.getOtschedule()));
			osm.setPatientIdentifier(schedule.getPatient().getPatientIdentifier().getIdentifier());
			osm.setPatientName(PatientUtils.getFullName(schedule.getPatient()));
			osm.setGender(schedule.getPatient().getGender());
			osm.setAge(schedule.getPatient().getAge());
			osm.setOrderId(schedule.getOpdOrderId());
			
			if (schedule.getEncounter() != null)
				osm.setEncounterId(schedule.getEncounter().getEncounterId());
			osm.setProcedure(schedule.getValueCoded().getName().toString());
			
			pDiagnosis = ots.getDiagnosisOTProcedure(schedule.getEncounter());
			osm.setpDiagnosis(pDiagnosis.getValueCoded().getName().toString());
		}
		return osm;
	}
}
