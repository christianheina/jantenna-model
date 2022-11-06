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

import com.christianheina.communication.jantenna.commons.ThetaPhi;

/**
 * Unit test for {@link ConjugateWeightAlgorithm}.
 * 
 * @author Christian Heina (developer@christianheina.com)
 */
@SuppressWarnings("javadoc")
public class GaussianAntennaModelTest {

    private static final double VERTICAL_HALF_POWER_BEAM_WIDTH = 30;
    private static final double HORIZONTAL_HALF_POWER_BEAM_WIDTH = 65;
    private static final Complex FIRST_VALUE = Complex.ONE;
    private static final Complex SECOND_VALUE = Complex.ONE;

    @Test
    public void defaultBuilderTest() {
        GaussianAntennaModel defaultModel = GaussianAntennaModel.newBuilder().build();
        Assert.assertEquals(defaultModel.getVerticalHalfPowerBeamWidth(),
                GaussianAntennaModel.DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(defaultModel.getHorizontalHalfPowerBeamWidth(),
                GaussianAntennaModel.DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH);
    }

    @Test
    public void nonDefaultBuilderTest() {
        GaussianAntennaModel nonDefaultModel = GaussianAntennaModel.newBuilder()
                .setHorizontalHalfPowerBeamWidth(HORIZONTAL_HALF_POWER_BEAM_WIDTH)
                .setVerticalHalfPowerBeamWidth(VERTICAL_HALF_POWER_BEAM_WIDTH).build();
        Assert.assertEquals(nonDefaultModel.getVerticalHalfPowerBeamWidth(), VERTICAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(nonDefaultModel.getHorizontalHalfPowerBeamWidth(), HORIZONTAL_HALF_POWER_BEAM_WIDTH);
    }

    @Test
    public void calculateAngleTest() {
        GaussianAntennaModel model = GaussianAntennaModel.newBuilder().build();
        Complex first = model.calculateAngle(ThetaPhi.fromDegrees(90, 0));
        Assert.assertEquals(first.getReal(), FIRST_VALUE.getReal());
        Assert.assertEquals(first.getImaginary(), FIRST_VALUE.getImaginary());
    }

}
