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
package org.eclipse.tractusx.valueaddedservice.dto.ShareDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.eclipse.tractusx.valueaddedservice.domain.Region;

import java.io.Serializable;

/**
 * A DTO for the {@link Region} entity.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShareRatingDTO implements Serializable {

    @Schema(example = "Fake Rating")
    private String dataSourceName = "";

    @Schema(example = "100")
    private Float score = 0F;

}