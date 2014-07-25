/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.j2objc.ast;

/**
 * Adds parentheses to a wrapped expression.
 */
public class ParenthesizedExpression extends Expression {

  private ChildLink<Expression> expression = ChildLink.create(Expression.class, this);

  public ParenthesizedExpression(org.eclipse.jdt.core.dom.ParenthesizedExpression jdtNode) {
    super(jdtNode);
    expression.set((Expression) TreeConverter.convert(jdtNode.getExpression()));
  }

  public ParenthesizedExpression(ParenthesizedExpression other) {
    super(other);
    expression.copyFrom(other.getExpression());
  }

  public Expression getExpression() {
    return expression.get();
  }

  @Override
  protected void acceptInner(TreeVisitor visitor) {
    if (visitor.visit(this)) {
      expression.accept(visitor);
    }
    visitor.endVisit(this);
  }

  @Override
  public ParenthesizedExpression copy() {
    return new ParenthesizedExpression(this);
  }
}
