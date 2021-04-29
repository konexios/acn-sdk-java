/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.model;

import java.io.Serializable;

import com.konexios.acs.client.model.ModelAbstract;

public class RTUFirmwareModels {

	public static class AvailableFirmwareVersion implements Serializable {
		private static final long serialVersionUID = -5425557624114264695L;

		private String softwareReleaseHid;
		private String softwareReleaseName;

		public AvailableFirmwareVersion() {
		}

		public AvailableFirmwareVersion(String softwareReleaseId, String softwareReleaseName) {
			this.softwareReleaseHid = softwareReleaseId;
			this.softwareReleaseName = softwareReleaseName;
		}

		public String getSoftwareReleaseHid() {
			return softwareReleaseHid;
		}

		public void setSoftwareReleaseHid(String softwareReleaseId) {
			this.softwareReleaseHid = softwareReleaseId;
		}

		public String getSoftwareReleaseName() {
			return softwareReleaseName;
		}

		public void setSoftwareReleaseName(String softwareReleaseName) {
			this.softwareReleaseName = softwareReleaseName;
		}
	}

	public static class RTURequestedFirmwareModel extends ModelAbstract<RTURequestedFirmwareModel> {
		private static final long serialVersionUID = 3456782850563131128L;

		private String ownerName;
		private String ownerEmail;
		private AvailableFirmwareVersion availableFirmwareVersion;
		private RightToUseStatus status;

		public AvailableFirmwareVersion getAvailableFirmwareVersion() {
			return availableFirmwareVersion;
		}

		public void setAvailableFirmwareVersion(AvailableFirmwareVersion availableFirmwareVersion) {
			this.availableFirmwareVersion = availableFirmwareVersion;
		}

		public RTURequestedFirmwareModel withAvailableFirmwareVersion(
				AvailableFirmwareVersion availableFirmwareVersion) {
			setAvailableFirmwareVersion(availableFirmwareVersion);

			return this;
		}

		public RTURequestedFirmwareModel withOwnerName(String ownerName) {
			setOwnerName(ownerName);
			return this;
		}

		public RTURequestedFirmwareModel withOwnerEmail(String ownerEmail) {
			setOwnerEmail(ownerEmail);
			return this;
		}

		public RTURequestedFirmwareModel withStatus(RightToUseStatus status) {
			setStatus(status);
			return this;
		}

		public String getOwnerName() {
			return ownerName;
		}

		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}

		public String getOwnerEmail() {
			return ownerEmail;
		}

		public void setOwnerEmail(String ownerEmail) {
			this.ownerEmail = ownerEmail;
		}

		public RightToUseStatus getStatus() {
			return status;
		}

		public void setStatus(RightToUseStatus status) {
			this.status = status;
		}

		@Override
		protected RTURequestedFirmwareModel self() {
			return this;
		}
	}

	public static class RTUFirmwareModel implements Serializable {
		private static final long serialVersionUID = 3456782850563131128L;

		private String deviceTypeHid;
		private String deviceTypeName;
		private long numberOfAssets;
		private String hardwareVersionName;
		private AvailableFirmwareVersion availableFirmwareVersion;
		private AvailableFirmwareVersion currentFirmwareVersion;

		public long getNumberOfAssets() {
			return numberOfAssets;
		}

		public void setNumberOfAssets(long numberOfAssets) {
			this.numberOfAssets = numberOfAssets;
		}

		public String getHardwareVersionName() {
			return hardwareVersionName;
		}

		public void setHardwareVersionName(String hardwareVersionName) {
			this.hardwareVersionName = hardwareVersionName;
		}

		public AvailableFirmwareVersion getAvailableFirmwareVersion() {
			return availableFirmwareVersion;
		}

		public void setAvailableFirmwareVersion(AvailableFirmwareVersion availableFirmwareVersion) {
			this.availableFirmwareVersion = availableFirmwareVersion;
		}

		public RTUFirmwareModel withNumberOfAssets(long numberOfAssets) {
			setNumberOfAssets(numberOfAssets);

			return this;
		}

		public RTUFirmwareModel withHardwareVersionName(String hardwareVersionName) {
			setHardwareVersionName(hardwareVersionName);

			return this;
		}

		public RTUFirmwareModel withCurrentFirmwareVersion(AvailableFirmwareVersion currentFirmwareVersion) {
			setCurrentFirmwareVersion(currentFirmwareVersion);

			return this;
		}

		public RTUFirmwareModel withAvailableFirmwareVersion(AvailableFirmwareVersion availableFirmwareVersion) {
			setAvailableFirmwareVersion(availableFirmwareVersion);

			return this;
		}

		public RTUFirmwareModel withDeviceTypeName(String deviceTypeName) {
			setDeviceTypeName(deviceTypeName);
			return this;
		}

		public RTUFirmwareModel withDeviceTypeHid(String deviceTypeHid) {
			setDeviceTypeHid(deviceTypeHid);
			return this;
		}

		public AvailableFirmwareVersion getCurrentFirmwareVersion() {
			return currentFirmwareVersion;
		}

		public void setCurrentFirmwareVersion(AvailableFirmwareVersion currentFirmwareVersion) {
			this.currentFirmwareVersion = currentFirmwareVersion;
		}

		public String getDeviceTypeHid() {
			return deviceTypeHid;
		}

		public void setDeviceTypeHid(String deviceTypeHid) {
			this.deviceTypeHid = deviceTypeHid;
		}

		public String getDeviceTypeName() {
			return deviceTypeName;
		}

		public void setDeviceTypeName(String deviceTypeName) {
			this.deviceTypeName = deviceTypeName;
		}
	}
}
