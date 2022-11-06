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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

import com.christianheina.communication.jantenna.commons.ElectricField;
import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.FieldType;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

abstract class AbstractAntennaModel implements AntennaModel {

    /**
     * The default phase value to use in calculations.
     */
    static final double DEFAULT_PHASE = 0;

    private double verticalHalfPowerBeamWidth;
    private double horizontalHalfPowerBeamWidth;

    protected AbstractAntennaModel(Builder<?> builder) {
        this.verticalHalfPowerBeamWidth = builder.verticalHalfPowerBeamWidth;
        this.horizontalHalfPowerBeamWidth = builder.horizontalHalfPowerBeamWidth;
    }

    @Override
    public Field calculateField(List<ThetaPhi> thetaPhiList) {
        List<Complex> fieldDataList = new ArrayList<>(thetaPhiList.size());
        for (ThetaPhi thetaPhi : thetaPhiList) {
            fieldDataList.add(calculateAngle(thetaPhi));
        }
        return Field.newBuilder().setThetaPhiList(thetaPhiList)
                .addElectricField(ElectricField.RELATIVE_GAIN, fieldDataList)
                // .setFreqency(frequency)
                .setFieldType(FieldType.FARFIELD).build();
    }

    /**
     * Retrieve vertical half power beam width (HPBW).
     * 
     * @return vertical half power beam width.
     */
    public double getVerticalHalfPowerBeamWidth() {
        return verticalHalfPowerBeamWidth;
    }

    /**
     * Retrieve horizontal half power beam width (HPBW).
     * 
     * @return horizontal half power beam width.
     */
    public double getHorizontalHalfPowerBeamWidth() {
        return horizontalHalfPowerBeamWidth;
    }

    static class Builder<T> {

        private double verticalHalfPowerBeamWidth = 0;
        private double horizontalHalfPowerBeamWidth = 0;

        protected Builder(double verticalHalfPowerBeamWidth, double horizontalHalfPowerBeamWidth) {
            this.verticalHalfPowerBeamWidth = verticalHalfPowerBeamWidth;
            this.horizontalHalfPowerBeamWidth = horizontalHalfPowerBeamWidth;
        }

        /**
         * Set vertical half power beam width (HPBW).
         * 
         * @param verticalHalfPowerBeamWidth
         *            the vertical half power beam width to set.
         * 
         * @return this instance of {@link T}.
         */
        @SuppressWarnings("unchecked")
        public T setVerticalHalfPowerBeamWidth(double verticalHalfPowerBeamWidth) {
            this.verticalHalfPowerBeamWidth = verticalHalfPowerBeamWidth;
            return (T) this;
        }

        /**
         * Set horizontal half power beam width (HPBW).
         * 
         * @param horizontalHalfPowerBeamWidth
         *            the horizontal half power beam width to set.
         * 
         * @return this instance of {@link T}.
         */
        @SuppressWarnings("unchecked")
        public T setHorizontalHalfPowerBeamWidth(double horizontalHalfPowerBeamWidth) {
            this.horizontalHalfPowerBeamWidth = horizontalHalfPowerBeamWidth;
            return (T) this;
        }

    }

}
