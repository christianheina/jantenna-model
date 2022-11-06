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
public class NrAntennaModelTest {

    private static final double VERTICAL_HALF_POWER_BEAM_WIDTH = 30;
    private static final double HORIZONTAL_HALF_POWER_BEAM_WIDTH = 50;
    private static final double VERTICAL_SIDELOBE_ATTENUATION = 60;
    private static final double MAXIMUM_GAIN = 20;
    private static final double MAXIMUM_ATTENUATION = 70;
    private static final Complex FIRST_VALUE = Complex.ONE;
    private static final Complex SECOND_VALUE = Complex.ONE;

    @Test
    public void defaultBuilderTest() {
        NrAntennaModel defaultModel = NrAntennaModel.newBuilder().build();
        Assert.assertEquals(defaultModel.getVerticalHalfPowerBeamWidth(),
                NrAntennaModel.DEFAULT_VERTICAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(defaultModel.getHorizontalHalfPowerBeamWidth(),
                NrAntennaModel.DEFAULT_HORIZONTAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(defaultModel.getVerticalSidelobeAttenuation(),
                NrAntennaModel.DEFAULT_VERTICAL_SIDELOBE_ATTENUATION);
        Assert.assertEquals(defaultModel.getMaximumGain(), NrAntennaModel.DEFAULT_MAXIMUM_GAIN);
        Assert.assertEquals(defaultModel.getMaximumAttenuation(), NrAntennaModel.DEFAULT_MAXIMUM_ATTENUATION);
    }

    @Test
    public void nonDefaultBuilderTest() {
        NrAntennaModel nonDefaultModel = NrAntennaModel.newBuilder()
                .setHorizontalHalfPowerBeamWidth(HORIZONTAL_HALF_POWER_BEAM_WIDTH)
                .setVerticalHalfPowerBeamWidth(VERTICAL_HALF_POWER_BEAM_WIDTH)
                .setMaximumAttenuation(MAXIMUM_ATTENUATION)
                .setVerticalSidelobeAttenuation(VERTICAL_SIDELOBE_ATTENUATION).setMaximumGain(MAXIMUM_GAIN)
                .build();
        Assert.assertEquals(nonDefaultModel.getVerticalHalfPowerBeamWidth(), VERTICAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(nonDefaultModel.getHorizontalHalfPowerBeamWidth(), HORIZONTAL_HALF_POWER_BEAM_WIDTH);
        Assert.assertEquals(nonDefaultModel.getVerticalSidelobeAttenuation(), VERTICAL_SIDELOBE_ATTENUATION);
        Assert.assertEquals(nonDefaultModel.getMaximumGain(), MAXIMUM_GAIN);
        Assert.assertEquals(nonDefaultModel.getMaximumAttenuation(), MAXIMUM_ATTENUATION);
    }

    @Test
    public void calculateAngleTest() {
        NrAntennaModel model = NrAntennaModel.newBuilder().setMaximumGain(0).build();
        Complex first = model.calculateAngle(ThetaPhi.fromDegrees(90, 0));
        Assert.assertEquals(first.abs(), FIRST_VALUE.abs());
        Assert.assertEquals(first.getReal(), FIRST_VALUE.getReal());
        Assert.assertEquals(first.getImaginary(), FIRST_VALUE.getImaginary());
    }

}
