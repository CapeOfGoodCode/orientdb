package com.orientechnologies.orient.core.sql.executor;

import com.orientechnologies.common.concur.OTimeoutException;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.executor.resultset.OResultSetMapper;
import com.orientechnologies.orient.core.sql.parser.OJson;

/** Created by luigidellaquila on 09/08/16. */
public class UpdateMergeStep extends AbstractExecutionStep {
  private final OJson json;

  public UpdateMergeStep(OJson json, OCommandContext ctx, boolean profilingEnabled) {
    super(ctx, profilingEnabled);
    this.json = json;
  }

  @Override
  public OResultSet syncPull(OCommandContext ctx, int nRecords) throws OTimeoutException {
    OResultSet upstream = getPrev().get().syncPull(ctx, nRecords);
    return new OResultSetMapper(upstream, (result) -> extracted(ctx, result));
  }

  private OResult extracted(OCommandContext ctx, OResult result) {
    if (result instanceof OResultInternal) {
      if (!(result.getElement().orElse(null) instanceof ODocument)) {
        ((OResultInternal) result).setElement(result.getElement().get().getRecord());
      }
      if (!(result.getElement().orElse(null) instanceof ODocument)) {
        return result;
      }
      handleMerge((ODocument) result.getElement().orElse(null), ctx);
    }
    return result;
  }

  private void handleMerge(ODocument record, OCommandContext ctx) {
    record.merge(json.toDocument(record, ctx), true, false);
  }

  @Override
  public String prettyPrint(int depth, int indent) {
    String spaces = OExecutionStepInternal.getIndent(depth, indent);
    StringBuilder result = new StringBuilder();
    result.append(spaces);
    result.append("+ UPDATE MERGE\n");
    result.append(spaces);
    result.append("  ");
    result.append(json);
    return result.toString();
  }
}
