/*
 * simulated-link-state-routing
 * Copyright (C) 2018, Logan Martel
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package socs.network.node;

import static socs.network.node.Router.MAX_PROCESS_PORT_NUMBER;
import static socs.network.node.Router.MIN_PROCESS_PORT_NUMBER;

import socs.network.utils.CommonUtils;

/**
 * Description summarizing state of router (ie. single node) in our network.
 */
class RouterDescription {

  /**
   * Private int constant to flag an invalid port number.
   */
  static final int INVALID_PORT_NUMBER = -1;

  /**
   * Int constant to flag minimum weight of transmission (ie. current router).
   */
  static final int TRANSMISSION_WEIGHT_TO_SELF = 0;

  /**
   * Process IP tied to this router's exposed socket instance.
   */
  final String processIpAddress;

  /**
   * Process port number tied to this router's exposed socket instance.
   */
  final short processPortNumber;

  /**
   * Unique IP address to identify the router in our simulated network space.
   */
  final String simulatedIpAddress;

  /**
   * Router's current discrete category of RouterStatus Enum.
   */
  RouterStatus status;

  /**
   * Stored weight of the cost to transmit a packet to this router.
   */
  final short weightToAttemptTransmission;

  /**
   * Constructor to instantiate a RouterDescription with required input parameters.
   */
  RouterDescription(String processIpAddress, short processPortNumber,
      String simulatedIpAddress, RouterStatus routerStatus, short weightToAttemptTransmission) {
    if (CommonUtils.isNullOrEmptyString(processIpAddress)) {
      throw new IllegalArgumentException(
          "Cannot instantiate router description with null or empty process IP address."
      );
    }
    if (CommonUtils.isNullOrEmptyString(simulatedIpAddress)) {
      throw new IllegalArgumentException(
          "Cannot instantiate router description with null or empty simulated IP address."
      );
    }
    if (RouterUtils.isPortNumberInvalid(processPortNumber)) {
      throw new IllegalArgumentException(
          "Cannot instantiate router description with port number '"
              + processPortNumber + "'.\nAll process ports in our network must fall in the range "
              + MIN_PROCESS_PORT_NUMBER + " to " + MAX_PROCESS_PORT_NUMBER + "."
      );
    }
    if (routerStatus == null) {
      throw new IllegalArgumentException(
          "Cannot instantiate router description with null RouterStatus."
      );
    }
    this.processIpAddress = processIpAddress;
    this.processPortNumber = processPortNumber;
    this.simulatedIpAddress = simulatedIpAddress;
    this.status = routerStatus;
    this.weightToAttemptTransmission = weightToAttemptTransmission;
  }
}
