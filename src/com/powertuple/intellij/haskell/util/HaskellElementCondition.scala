/*
 * Copyright 2015 Rik van der Kleij
 *
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

package com.powertuple.intellij.haskell.util

import com.intellij.openapi.util.Condition
import com.intellij.psi.PsiElement
import com.powertuple.intellij.haskell.psi._

object HaskellElementCondition {

  final val ImportModuleCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellImportModule => true
        case _ => false
      }
    }
  }

  final val ImportModuleDeclarationCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellImportDeclaration => true
        case _ => false
      }
    }
  }

  final val ModuleDeclarationCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellModuleDeclaration => true
        case _ => false
      }
    }
  }

  final val NamedElementCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellNamedElement => true
        case _ => false
      }
    }
  }

  final val QVarConOpElementCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellQVarConOpElement => true
        case _ => false
      }
    }
  }

  final val DeclarationElementCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellDeclarationElement => true
        case _ => false
      }
    }
  }

  final val QualifiedElementCondition = new Condition[PsiElement]() {
    override def value(psiElement: PsiElement): Boolean = {
      psiElement match {
        case _: HaskellQvarId => true
        case _: HaskellQvarSym => true
        case _: HaskellQconId => true
        case _: HaskellQconSym => true
        case _ => false
      }
    }
  }
}
