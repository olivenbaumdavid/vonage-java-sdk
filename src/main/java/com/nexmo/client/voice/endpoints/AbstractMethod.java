package com.nexmo.client.voice.endpoints;/*
 * Copyright (c) 2011-2016 Nexmo Inc
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

import com.nexmo.client.HttpWrapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;

public abstract class AbstractMethod<RequestT, ResultT> implements Method<RequestT, ResultT> {
    private final HttpWrapper httpWrapper;

    public AbstractMethod(HttpWrapper httpWrapper) {
        this.httpWrapper = httpWrapper;
    }

    // TODO: Consider wrapping IOException in a nexmo-specific transport exception.
    public ResultT execute(RequestT request) throws IOException {
        return parseResponse(this.httpWrapper.getHttpClient().execute(makeRequest(request)));
    }

    public abstract HttpUriRequest makeRequest(RequestT request);

    public abstract ResultT parseResponse(HttpResponse response);

    public void setHttpClient(HttpClient client) {
        this.httpWrapper.setHttpClient(client);
    }
}