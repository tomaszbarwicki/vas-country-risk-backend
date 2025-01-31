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
package org.eclipse.tractusx.valueaddedservice.service;

import org.eclipse.tractusx.valueaddedservice.domain.DataSource;
import org.eclipse.tractusx.valueaddedservice.domain.enumeration.Type;
import org.eclipse.tractusx.valueaddedservice.dto.CompanyUserDTO;
import org.eclipse.tractusx.valueaddedservice.dto.DataSourceDTO;
import org.eclipse.tractusx.valueaddedservice.repository.DataSourceRepository;
import org.eclipse.tractusx.valueaddedservice.service.mapper.DataSourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DataSource}.
 */
@Service
public class DataSourceService {

    private final Logger log = LoggerFactory.getLogger(DataSourceService.class);

    private final DataSourceRepository dataSourceRepository;

    private final DataSourceMapper dataSourceMapper;

    public DataSourceService(DataSourceRepository dataSourceRepository, DataSourceMapper dataSourceMapper) {
        this.dataSourceRepository = dataSourceRepository;
        this.dataSourceMapper = dataSourceMapper;
    }


    //API to get Ratings by Year
    public List<DataSourceDTO> findRatingsByYearAndTypeGlobal(Integer year) {
        return dataSourceMapper.toDto(dataSourceRepository.findByYearPublishedAndType(year, Type.Global));
    }

    //API to get Rating name by User
    public List<DataSourceDTO> findRatingByYearAndUser(Integer year, CompanyUserDTO companyUserDTO) {
        return dataSourceMapper.toDto(dataSourceRepository.findByYearPublishedAndCompanyUserNameAndCompanyUserEmailAndCompanyUserCompanyNameAndType(year,companyUserDTO.getName(),companyUserDTO.getEmail(), companyUserDTO.getCompanyName(),Type.Custom));
    }

    public List<DataSourceDTO> findRatingByUser( CompanyUserDTO companyUserDTO) {
        return dataSourceMapper.toDto(dataSourceRepository.findByCompanyUserNameAndCompanyUserEmailAndCompanyUserCompanyNameOrTypeOrTypeAndCompanyUserCompanyName(companyUserDTO.getName(),companyUserDTO.getEmail(), companyUserDTO.getCompanyName(),Type.Global,Type.Company,companyUserDTO.getCompanyName()));
    }

    public List<DataSourceDTO> findByYearPublishedAndCompanyUserCompanyNameAndType(Integer year, CompanyUserDTO companyUserDTO, Type type) {
        return dataSourceMapper.toDto(dataSourceRepository.findByYearPublishedAndCompanyUserCompanyNameAndType(year, companyUserDTO.getCompanyName(), type));
    }


    /**
     * Save a dataSource.
     *
     * @param dataSourceDTO the entity to save.
     * @return the persisted entity.
     */
    public DataSourceDTO save(DataSourceDTO dataSourceDTO) {
        log.debug("Request to save DataSource : {}", dataSourceDTO);
        DataSource dataSource = dataSourceMapper.toEntity(dataSourceDTO);
        dataSource = dataSourceRepository.save(dataSource);
        return dataSourceMapper.toDto(dataSource);
    }


    /**
     * Partially update a dataSource.
     *
     * @param dataSourceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DataSourceDTO> partialUpdate(DataSourceDTO dataSourceDTO) {
        log.debug("Request to partially update DataSource : {}", dataSourceDTO);

        return dataSourceRepository
            .findById(dataSourceDTO.getId())
            .map(existingDataSource -> {
                dataSourceMapper.partialUpdate(existingDataSource, dataSourceDTO);

                return existingDataSource;
            })
            .map(dataSourceRepository::save)
            .map(dataSourceMapper::toDto);
    }

    /**
     * Get all the dataSources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */

    public Page<DataSourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataSources");
        return dataSourceRepository.findAll(pageable).map(dataSourceMapper::toDto);
    }


    public List<DataSourceDTO> findAll() {
        log.debug("Request to get all DataSources");
        return dataSourceMapper.toDto(dataSourceRepository.findAll());
    }

    /**
     * Get one dataSource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */

    public Optional<DataSourceDTO> findOne(Long id) {
        log.debug("Request to get DataSource : {}", id);
        return dataSourceRepository.findById(id).map(dataSourceMapper::toDto);
    }

    /**
     * Delete the dataSource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DataSource : {}", id);
        dataSourceRepository.deleteById(id);
    }
}
