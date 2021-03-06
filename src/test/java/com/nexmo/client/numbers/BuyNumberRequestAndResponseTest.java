/*
 * Copyright (c) 2011-2017 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.client.numbers;

import com.nexmo.client.NexmoUnexpectedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BuyNumberRequestAndResponseTest {
    @Test
    public void testDeserialization() {
        BuyNumberResponse response = BuyNumberResponse.fromJson("{\n" +
                "  \"error-code\":\"200\",\n" +
                "  \"error-code-label\":\"success\"\n" +
                "}");
        assertEquals("200", response.getErrorCode());
        assertEquals("success", response.getErrorCodeLabel());
    }

    @Test
    public void testBadJson() throws Exception {
        try {
            BuyNumberResponse.fromJson("this-is-nonsense");
            fail("NexmoUnexpectedException should be raised for bad JSON data.");
        } catch (NexmoUnexpectedException nue) {
            // Expected
        }
    }

    @Test
    public void testFilterValues() throws Exception {
        BuyNumberRequest request = new BuyNumberRequest("YY", "447700900000");
        assertEquals("YY", request.getCountry());
        assertEquals("447700900000", request.getMsisdn());
    }
}
