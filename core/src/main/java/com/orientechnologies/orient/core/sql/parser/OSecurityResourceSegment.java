/* Generated By:JJTree: Do not edit this line. OSecurityResourceSegment.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

public class OSecurityResourceSegment extends SimpleNode {

  protected boolean star = false;
  protected boolean cluster = false;
  protected OIdentifier identifier;

  protected OSecurityResourceSegment next;

  public OSecurityResourceSegment(int id) {
    super(id);
  }

  public OSecurityResourceSegment(OrientSql p, int id) {
    super(p, id);
  }


  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public boolean isStar() {
    return star;
  }

  public boolean isCluster() {
    return cluster;
  }

  public OIdentifier getIdentifier() {
    return identifier;
  }

  public OSecurityResourceSegment getNext() {
    return next;
  }


  @Override
  public String toString() {
    String result;
    if (this.star) {
      result = "*";
    } else if (this.cluster) {
      result = "cluster";
    } else {
      result = identifier.toString();
    }
    if (next != null) {
      result += ".";
      result += next.toString();
    }
    return result;
  }
}
/* JavaCC - OriginalChecksum=f51870252b37ccb5ff69ec19ed9687ab (do not edit this line) */
