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
package com.christianheina.communication.jantenna.model;

import java.util.List;

import org.apache.commons.math3.complex.Complex;

import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Interface for antenna model.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public interface AntennaModel {

    /**
     * Create instance of {@link GaussianAntennaModel.Builder}.
     * 
     * @return new {@link GaussianAntennaModel.Builder} instance.
     */
    public static GaussianAntennaModel.Builder newGaussianAntennaModelBuilder() {
        return GaussianAntennaModel.newBuilder();
    }

    /**
     * Create instance of {@link NrAntennaModel.Builder}.
     * 
     * @return new {@link NrAntennaModel.Builder} instance.
     */
    public static NrAntennaModel.Builder newNrAntennaModelBuilder() {
        return NrAntennaModel.newBuilder();
    }

    /**
     * Calculate antenna model.
     * 
     * @param thetaPhiList
     *            the theta and phi angles where the model is calculated.
     * 
     * @return new instance of {@link Field} containing the model.
     */
    Field calculateField(List<ThetaPhi> thetaPhiList);

    /**
     * Calculate antenna model at specific angle.
     * 
     * @param thetaPhi
     *            the theta and phi angle where the model is calculated.
     * 
     * @return {@link Complex} value representing the model at given angle.
     */
    Complex calculateAngle(ThetaPhi thetaPhi);

}
