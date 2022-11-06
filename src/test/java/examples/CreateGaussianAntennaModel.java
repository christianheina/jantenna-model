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
import com.christianheina.communication.jantenna.model.GaussianAntennaModel;

/**
 * Create new model using {@link GaussianAntennaModel}
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class CreateGaussianAntennaModel {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Create Gaussian model with input parameters
        AntennaModel model = AntennaModel.newGaussianAntennaModelBuilder().setHorizontalHalfPowerBeamWidth(65)
                .setVerticalHalfPowerBeamWidth(35).build();
        // Angles to generate antenna model at
        List<ThetaPhi> angleList = ThetaPhi.equallySpacedSphere(1);
        // Generate gaussian model
        Field field = model.calculateField(angleList);
    }

}
