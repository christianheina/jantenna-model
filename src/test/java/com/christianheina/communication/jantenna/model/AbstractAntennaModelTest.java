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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.christianheina.communication.jantenna.commons.ElectricField;
import com.christianheina.communication.jantenna.commons.Field;
import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Unit test for {@link ConjugateWeightAlgorithm}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class AbstractAntennaModelTest {

    private static final double VERTICAL_HPBW = 30;
    private static final double HORIZONTAL_HPBW = 65;
    private static final ThetaPhi MAXIMUM_DIRECTION = ThetaPhi.fromDegrees(90, 0);
    private static final Complex MAXIMUM_VALUE = Complex.ONE;

    @Test
    public void getVerticalHpbwTest() {
        AbstractAntennaModel model = GaussianAntennaModel.newBuilder().setHorizontalHalfPowerBeamWidth(HORIZONTAL_HPBW)
                .setVerticalHalfPowerBeamWidth(VERTICAL_HPBW).build();
        double hpbw = model.getVerticalHalfPowerBeamWidth();
        Assert.assertEquals(hpbw, VERTICAL_HPBW);
    }

    @Test
    public void getHorizontalHpbwTest() {
        AbstractAntennaModel model = GaussianAntennaModel.newBuilder().setHorizontalHalfPowerBeamWidth(HORIZONTAL_HPBW)
                .setVerticalHalfPowerBeamWidth(VERTICAL_HPBW).build();
        double hpbw = model.getHorizontalHalfPowerBeamWidth();
        Assert.assertEquals(hpbw, HORIZONTAL_HPBW);
    }

    @Test
    public void calculateAngleFieldTest() {
        AbstractAntennaModel model = GaussianAntennaModel.newBuilder().build();
        Field field = model.calculateField(ThetaPhi.equallySpacedSphere(1));
        Complex max = field.getElectricField(ElectricField.RELATIVE_GAIN).get(0);
        ThetaPhi maxDir = field.getThetaPhiList().get(0);
        for (int i = 0; i < field.getElectricField(ElectricField.RELATIVE_GAIN).size(); i++) {
            Complex val = field.getElectricField(ElectricField.RELATIVE_GAIN).get(i);
            ThetaPhi dir = field.getThetaPhiList().get(i);
            if (val.abs() > max.abs()) {
                max = val;
                maxDir = dir;
            }
        }
        Assert.assertEquals(maxDir.getTheta(), MAXIMUM_DIRECTION.getTheta());
        Assert.assertEquals(maxDir.getPhi(), MAXIMUM_DIRECTION.getPhi());
        Assert.assertEquals(max.getReal(), MAXIMUM_VALUE.getReal());
        Assert.assertEquals(max.getImaginary(), MAXIMUM_VALUE.getImaginary());
    }

}
