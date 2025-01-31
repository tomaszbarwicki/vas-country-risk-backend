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
package org.eclipse.tractusx.valueaddedservice.dto;

import lombok.*;
import org.eclipse.tractusx.valueaddedservice.domain.CompanyGates;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link CompanyGates} entity.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CompanyGatesDTO implements Serializable {

    private Long id;

    @NotNull
    private String gateName;

    @NotNull
    private String companyGateValue;



}
