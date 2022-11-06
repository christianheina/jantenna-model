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

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Antenna model using gaussian algorithm.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class GaussianAntennaModel extends AbstractAntennaModel {

    /**
     * Default vertical half power beam width (HPBW).
     */
    public static final double DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH = 10;
    /**
     * Default horizontal half power beam width (HPBW).
     */
    public static final double DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH = 10;

    private static final double SK = -2 * Math.log(2);

    private GaussianAntennaModel(Builder builder) {
        super(builder);
    }

    @Override
    public Complex calculateAngle(ThetaPhi thetaPhi) {
        double r = Math.exp(SK * Math.pow(Math.toDegrees(thetaPhi.getPhi()) / getHorizontalHalfPowerBeamWidth(), 2))
                * Math.exp(
                        SK * Math.pow((Math.toDegrees(thetaPhi.getTheta()) - 90) / getVerticalHalfPowerBeamWidth(), 2));
        return ComplexUtils.polar2Complex(r, DEFAULT_PHASE);
    }

    static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for {@link GaussianAntennaModel}.
     * 
     * @author Christian Heina (developer@christianheina.com)
     */
    public static class Builder extends AbstractAntennaModel.Builder<Builder> {

        private Builder() {
            super(DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH, DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH);
        }

        /**
         * Build new instance of {@link GaussianAntennaModel} using this instance of
         * {@link GaussianAntennaModel.Builder}.
         * 
         * @return new instance of {@link GaussianAntennaModel}.
         */
        public GaussianAntennaModel build() {
            return new GaussianAntennaModel(this);
        }

    }

}
