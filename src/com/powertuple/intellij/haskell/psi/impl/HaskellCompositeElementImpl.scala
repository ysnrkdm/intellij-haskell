/*
 * Copyright 2015 Rik van der Kleij

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

package com.powertuple.intellij.haskell.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.{PsiElement, ResolveState}
import com.intellij.psi.scope.PsiScopeProcessor
import com.powertuple.intellij.haskell.psi.HaskellCompositeElement
import org.jetbrains.annotations.NotNull

object HaskellCompositeElementImpl {
  private[impl] def processDeclarations(@NotNull element: PsiElement, @NotNull processor: PsiScopeProcessor, @NotNull state: ResolveState, lastParent: PsiElement, @NotNull place: PsiElement): Boolean = {
    if (!processor.execute(element, state)) {
      false
    }
    else {
      ResolveUtil.processChildren(element, processor, state, lastParent, place)
    }
  }
}

class HaskellCompositeElementImpl(node: ASTNode) extends ASTWrapperPsiElement(node) with HaskellCompositeElement {

  override def toString: String = {
    getNode.getElementType.toString
  }

  override def processDeclarations(@NotNull processor: PsiScopeProcessor, @NotNull state: ResolveState, lastParent: PsiElement, @NotNull place: PsiElement): Boolean = {
    HaskellCompositeElementImpl.processDeclarations(this, processor, state, lastParent, place)
  }
}