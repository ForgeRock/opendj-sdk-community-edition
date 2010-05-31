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

import javax.net.ssl.SSLContext;

import org.opends.sdk.ByteString;
import org.opends.sdk.DecodeException;
import org.opends.sdk.DecodeOptions;
import org.opends.sdk.controls.Control;
import org.opends.sdk.controls.ControlDecoder;
import org.opends.sdk.responses.ExtendedResult;
import org.opends.sdk.responses.ExtendedResultDecoder;



/**
 * The start TLS extended request as defined in RFC 4511. The Start Transport
 * Layer Security (StartTLS) operation's purpose is to initiate installation of
 * a TLS layer.
 *
 * @see <a href="http://tools.ietf.org/html/rfc4511">RFC 4511 - Lightweight
 *      Directory Access Protocol (LDAP): The Protocol </a>
 */
public interface StartTLSExtendedRequest extends
    ExtendedRequest<ExtendedResult>
{

  /**
   * The OID for the start TLS extended operation request.
   */
  public static final String OID = "1.3.6.1.4.1.1466.20037";

  /**
   * A decoder which can be used to decode start TLS extended operation
   * requests.
   */
  public static final ExtendedRequestDecoder<StartTLSExtendedRequest,
                                             ExtendedResult> DECODER =
    new StartTLSExtendedRequestImpl.RequestDecoder();



  /**
   * {@inheritDoc}
   */
  StartTLSExtendedRequest addControl(Control control)
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
  ExtendedResultDecoder<ExtendedResult> getResultDecoder();



  /**
   * Returns the SSLContext that should be used when installing the TLS layer.
   *
   * @return The SSLContext that should be used when installing the TLS layer.
   */
  SSLContext getSSLContext();



  /**
   * {@inheritDoc}
   */
  ByteString getValue();



  /**
   * {@inheritDoc}
   */
  boolean hasValue();



  /**
   * Sets the SSLContext that should be used when installing the TLS layer.
   *
   * @param sslContext
   *          The SSLContext that should be used when installing the TLS layer.
   * @return This startTLS request.
   */
  StartTLSExtendedRequest setSSLContext(SSLContext sslContext);
}