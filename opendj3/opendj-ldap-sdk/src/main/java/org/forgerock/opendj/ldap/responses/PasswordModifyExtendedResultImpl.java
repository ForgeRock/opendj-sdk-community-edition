/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opendj3/legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opendj3/legal-notices/CDDLv1_0.txt.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2010 Sun Microsystems, Inc.
 */

package org.forgerock.opendj.ldap.responses;



import java.io.IOException;

import org.forgerock.opendj.asn1.ASN1;
import org.forgerock.opendj.asn1.ASN1Writer;
import org.forgerock.opendj.ldap.ByteString;
import org.forgerock.opendj.ldap.ByteStringBuilder;
import org.forgerock.opendj.ldap.ResultCode;



/**
 * Password modify extended result implementation.
 */
final class PasswordModifyExtendedResultImpl extends
    AbstractExtendedResult<PasswordModifyExtendedResult> implements
    PasswordModifyExtendedResult
{
  private ByteString password;

  /**
   * The ASN.1 element type that will be used to encode the genPasswd component
   * in a password modify extended response.
   */
  private static final byte TYPE_PASSWORD_MODIFY_GENERATED_PASSWORD =
      (byte) 0x80;



  // Instantiation via factory.
  PasswordModifyExtendedResultImpl(final ResultCode resultCode)
  {
    super(resultCode);
  }



  /**
   * Creates a new password modify extended result that is an exact copy of the
   * provided result.
   *
   * @param passwordModifyExtendedResult
   *          The password modify extended result to be copied.
   * @throws NullPointerException
   *           If {@code passwordModifyExtendedResult} was {@code null} .
   */
  PasswordModifyExtendedResultImpl(
      final PasswordModifyExtendedResult passwordModifyExtendedResult)
      throws NullPointerException
  {
    super(passwordModifyExtendedResult);
    this.password = passwordModifyExtendedResult.getGeneratedPassword();
  }



  /**
   * {@inheritDoc}
   */
  public ByteString getGeneratedPassword()
  {
    return password;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public String getOID()
  {
    // No response name defined.
    return null;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public ByteString getValue()
  {
    if (password != null)
    {
      final ByteStringBuilder buffer = new ByteStringBuilder();
      final ASN1Writer writer = ASN1.getWriter(buffer);

      try
      {
        writer.writeStartSequence();
        writer.writeOctetString(TYPE_PASSWORD_MODIFY_GENERATED_PASSWORD,
            password);
        writer.writeEndSequence();
      }
      catch (final IOException ioe)
      {
        // This should never happen unless there is a bug somewhere.
        throw new RuntimeException(ioe);
      }

      return buffer.toByteString();
    }
    return null;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasValue()
  {
    return password != null;
  }



  /**
   * {@inheritDoc}
   */
  public PasswordModifyExtendedResult setGeneratedPassword(
      final ByteString password) throws UnsupportedOperationException
  {
    this.password = password;
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public PasswordModifyExtendedResult setGeneratedPassword(
      final char[] password) throws UnsupportedOperationException
  {
    this.password = (password != null) ? ByteString.valueOf(password) : null;
    return this;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("PasswordModifyExtendedResponse(resultCode=");
    builder.append(getResultCode());
    builder.append(", matchedDN=");
    builder.append(getMatchedDN());
    builder.append(", diagnosticMessage=");
    builder.append(getDiagnosticMessage());
    builder.append(", referrals=");
    builder.append(getReferralURIs());
    if (password != null)
    {
      builder.append(", genPassword=");
      builder.append(password);
    }
    builder.append(", controls=");
    builder.append(getControls());
    builder.append(")");
    return builder.toString();
  }
}