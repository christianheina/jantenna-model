/*
 * Copyright 2021 Christian Heina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples;

import java.util.List;

import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;
import com.christianheina.communication.jantenna.model.AntennaModel;
import com.christianheina.communication.jantenna.model.enums.NrAntennaPolarizationModel;

/**
 * Save field to file example
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class CreateNrAntennaModel {

    public static void main(String[] args) {
        // Create NR model with input parameters
        AntennaModel model = AntennaModel.newNrAntennaModelBuilder().setHorizontalHalfPowerBeamWidth(65)
                .setVerticalHalfPowerBeamWidth(35).setVerticalSidelobeAttenuation(30).setMaximumGain(10)
                .setMaximumAttenuation(30).setPolarizationSlantAngle(0)
                .setPolarizationModel(NrAntennaPolarizationModel.MODEL_2).build();
        // Angles for generating antenna model
        List<ThetaPhi> angleList = ThetaPhi.equallySpacedSphere(1);
        // Generate NR model
        Field field = model.calculateField(angleList);
    }

}
