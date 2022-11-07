/*
 * Copyright 2022 Christian Heina
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
package com.christianheina.communication.jantenna.model.enums;

import com.christianheina.communication.jantenna.model.NrAntennaModel;

/**
 * Polarization models for {@link NrAntennaModel} as described in 3GPP TR 38.901 chapter 7.3.2.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public enum NrAntennaPolarizationModel {
    /**
     * Polarization model 2 as described in 3GPP TR 38.901 figure 7.3-4 and 7.3-5.
     */
    MODEL_2(2),
    /**
     * Polarization model 1 as described in 3GPP TR 38.901 figure 7.3-3.
     */
    MODEL_1(1);

    private int modelValue;

    private NrAntennaPolarizationModel(int modelValue) {
        this.modelValue = modelValue;
    }

    /**
     * Retrieve integer representation of model.
     * 
     * @return model integer value.
     */
    public int getModelValue() {
        return modelValue;
    }
}
