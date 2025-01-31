/********************************************************************************
* Copyright (c) 2022,2023 BMW Group AG 
* Copyright (c) 2022,2023 Contributors to the Eclipse Foundation
*
* See the NOTICE file(s) distributed with this work for additional
* information regarding copyright ownership.
*
* This program and the accompanying materials are made available under the
* terms of the Apache License, Version 2.0 which is available at
* https://www.apache.org/licenses/LICENSE-2.0.
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations
* under the License.
*
* SPDX-License-Identifier: Apache-2.0
********************************************************************************/
package org.eclipse.tractusx.valueaddedservice.service.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public class InvokeService {

    @Autowired
    WebClient webClient;

    public <T> Mono<List<T>> executeRequest(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> responseType, Function<String, List<T>> mappingFunction) {
        return webClient.method(httpMethod)
                .uri(url)
                .headers(headers -> headers.addAll(httpEntity.getHeaders()))
                .body(BodyInserters.fromValue(httpEntity.getBody()))
                .retrieve()
                .bodyToMono(String.class)
                .map(mappingFunction)
                .onErrorResume(e -> {
                    log.error("error url {} message {}", url, e.getMessage());
                    return Mono.just(new ArrayList<>());
                });
    }
}


