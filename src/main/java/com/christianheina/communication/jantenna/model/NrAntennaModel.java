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
 * Antenna model using model described in 3GPP TR 38.901 chapter 7.3.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
public class NrAntennaModel extends AbstractAntennaModel {

    /**
     * Default vertical half power beam width (HPBW) as described in 3GPP TR 38.901 Table 7.3-1.
     */
    public static final double DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH = 65;

    /**
     * Default horizontal half power beam width (HPBW) as described in 3GPP TR 38.901 Table 7.3-1.
     */
    public static final double DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH = 65;

    /**
     * Default side-lobe attenuation (SLA) in vertical direction as described in 3GPP TR 38.901 Table 7.3-1.
     */
    public static final double DEFAULT_VERTICAL_SIDELOBE_ATTENUATION = 30;

    /**
     * Default maximum gain as described in 3GPP TR 38.901 Table 7.3-1.
     */
    public static final double DEFAULT_MAXIMUM_GAIN = 8;

    /**
     * Default maximum attenuation as described in 3GPP TR 38.901 Table 7.3-1.
     */
    public static final double DEFAULT_MAXIMUM_ATTENUATION = 30;

    private double verticalSidelobeAttenuation;
    private double maximumGain;
    private double maximumAttenuation;

    private NrAntennaModel(Builder builder) {
        super(builder);
        this.verticalSidelobeAttenuation = builder.verticalSidelobeAttenuation;
        this.maximumGain = builder.maximumGain;
        this.maximumAttenuation = builder.maximumAttenuation;
    }

    @Override
    public Complex calculateAngle(ThetaPhi thetaPhi) {
        double at = calculateA(Math.toDegrees(thetaPhi.getTheta()) - 90, getVerticalHalfPowerBeamWidth(),
                verticalSidelobeAttenuation);
        double ap = calculateA(Math.toDegrees(thetaPhi.getPhi()), getHorizontalHalfPowerBeamWidth(),
                maximumAttenuation);
        double r = Math.pow(10, (maximumGain + compareReturnLow(-1 * (at + ap), maximumAttenuation)) / 20);
        return ComplexUtils.polar2Complex(r, DEFAULT_PHASE);
    }

    private static double calculateA(double angle, double hpbw, double def) {
        double a = 12 * Math.pow(angle / hpbw, 2);
        return compareReturnLow(a, def);
    }

    private static double compareReturnLow(double value1, double value2) {
        double lowest = value1;
        if (value1 > value2) {
            lowest = value2;
        }
        return -1 * lowest;
    }

    /**
     * Retrieve side-lobe attenuation in vertical direction (SLA).
     * 
     * @return side-lobe attenuation in vertical direction.
     */
    public double getVerticalSidelobeAttenuation() {
        return verticalSidelobeAttenuation;
    }

    /**
     * Retrieve maximum gain.
     * 
     * @return maximum gain.
     */
    public double getMaximumGain() {
        return maximumGain;
    }

    /**
     * Retrieve maximum attenuation.
     * 
     * @return maximum attenuation.
     */
    public double getMaximumAttenuation() {
        return maximumAttenuation;
    }

    static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for {@link NrAntennaModel}.
     * 
     * @author Christian Heina (developer@christianheina.com)
     */
    public static class Builder extends AbstractAntennaModel.Builder<Builder> {

        private double verticalSidelobeAttenuation = DEFAULT_VERTICAL_SIDELOBE_ATTENUATION;
        private double maximumGain = DEFAULT_MAXIMUM_GAIN;
        private double maximumAttenuation = DEFAULT_MAXIMUM_ATTENUATION;

        private Builder() {
            super(DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH, DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH);
        }

        /**
         * Set side-lobe attenuation in vertical direction (SLA).
         * 
         * @param verticalSidelobeAttenuation
         *            the sidelobeAttenuation to set.
         * 
         * @return this instance of {@link Builder}.
         */
        public Builder setVerticalSidelobeAttenuation(double verticalSidelobeAttenuation) {
            this.verticalSidelobeAttenuation = verticalSidelobeAttenuation;
            return this;
        }

        /**
         * Set maximum gain.
         * 
         * @param maximumGain
         *            the maximum gain to set.
         * 
         * @return this instance of {@link Builder}.
         */
        public Builder setMaximumGain(double maximumGain) {
            this.maximumGain = maximumGain;
            return this;
        }

        /**
         * Set maximum attenuation.
         * 
         * @param maximumAttenuation
         *            the maximum attenuation to set.
         * 
         * @return this instance of {@link Builder}.
         */
        public Builder setMaximumAttenuation(double maximumAttenuation) {
            this.maximumAttenuation = maximumAttenuation;
            return this;
        }

        /**
         * Build new instance of {@link NrAntennaModel} using this instance of {@link NrAntennaModel.Builder}.
         * 
         * @return new instance of {@link NrAntennaModel}.
         */
        public NrAntennaModel build() {
            return new NrAntennaModel(this);
        }

    }

}
