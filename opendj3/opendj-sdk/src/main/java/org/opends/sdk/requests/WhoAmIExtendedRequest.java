/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2010 Sun Microsystems, Inc.
 */

package org.opends.sdk.requests;



import java.util.List;

import org.opends.sdk.ByteString;
import org.opends.sdk.DecodeException;
import org.opends.sdk.DecodeOptions;
import org.opends.sdk.controls.Control;
import org.opends.sdk.controls.ControlDecoder;
import org.opends.sdk.responses.ExtendedResultDecoder;
import org.opends.sdk.responses.WhoAmIExtendedResult;



/**
 *The who am I extended request as defined in RFC 4532. This operation allows
 * clients to obtain the primary authorization identity, in its primary form,
 * that the server has associated with the user or application entity.
 * <p>
 * This operation may preferable to the Authorization Identity Controls
 * mechanism defined in RFC 3829, which uses Bind request and response controls
 * to request and return the authorization identity. Bind controls are not
 * protected by security layers established by the Bind operation that includes
 * them. While it is possible to establish security layers using StartTLS prior
 * to the Bind operation, it is often desirable to use security layers
 * established by the Bind operation. An extended operation sent after a Bind
 * operation is protected by the security layers established by the Bind
 * operation.
 *
 * @see WhoAmIExtendedResult
 * @see org.opends.sdk.controls.AuthorizationIdentityRequestControl
 * @see <a href="http://tools.ietf.org/html/rfc4532">RFC 4532 - Lightweight
 *      Directory Access Protocol (LDAP) "Who am I?" Operation </a>
 * @see <a href="http://tools.ietf.org/html/rfc3829">RFC 3829 - Lightweight
 *      Directory Access Protocol (LDAP) Authorization Identity Request and
 *      Response Controls </a>
 */
public interface WhoAmIExtendedRequest extends
    ExtendedRequest<WhoAmIExtendedResult>
{

  /**
   * The OID for the who am I extended operation request.
   */
  public static final String OID = "1.3.6.1.4.1.4203.1.11.3";

  /**
   * A decoder which can be used to decode who am I extended operation requests.
   */
  public static final ExtendedRequestDecoder<WhoAmIExtendedRequest,
                                             WhoAmIExtendedResult> DECODER =
    new WhoAmIExtendedRequestImpl.RequestDecoder();



  /**
   * {@inheritDoc}
   */
  WhoAmIExtendedRequest addControl(Control control)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * {@inheritDoc}
   */
  <C extends Control> C getControl(ControlDecoder<C> decoder,
      DecodeOptions options) throws NullPointerException, DecodeException;



  /**
   * {@inheritDoc}
   */
  List<Control> getControls();



  /**
   * {@inheritDoc}
   */
  String getOID();



  /**
   * {@inheritDoc}
   */
  ExtendedResultDecoder<WhoAmIExtendedResult> getResultDecoder();



  /**
   * {@inheritDoc}
   */
  ByteString getValue();



  /**
   * {@inheritDoc}
   */
  boolean hasValue();
}