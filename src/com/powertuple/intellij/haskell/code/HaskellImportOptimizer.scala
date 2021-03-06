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

package com.powertuple.intellij.haskell.code

import com.intellij.lang.ImportOptimizer
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.powertuple.intellij.haskell.HaskellFile
import com.powertuple.intellij.haskell.external.GhcModCheck
import com.powertuple.intellij.haskell.util.{HaskellElementCondition, LineColumnPosition}

class HaskellImportOptimizer extends ImportOptimizer {

  override def supports(file: PsiFile): Boolean = file.isInstanceOf[HaskellFile]

  override def processFile(file: PsiFile): Runnable = {
    new Runnable {
      override def run(): Unit = {
        val problems = GhcModCheck.check(file.getProject, file.getVirtualFile.getCanonicalPath).problems
        val redundantImports = problems.filter(p => p.getNormalizedMessage match {
          case HaskellImportOptimizer.WarningRedundantImport() => true
          case _ => false
        })

        val redundantImportModuleOffsets = redundantImports.map(p => LineColumnPosition.getOffset(file, LineColumnPosition(p.lineNr, p.columnNr))).flatten
        val redundantModuleDeclarations = redundantImportModuleOffsets.map(offset => file.findElementAt(offset)).map(e => PsiTreeUtil.findFirstParent(e, HaskellElementCondition.ImportModuleDeclarationCondition))
        redundantModuleDeclarations.foreach { me =>
          me.delete()
        }
      }
    }
  }
}

object HaskellImportOptimizer {
  final val WarningRedundantImport = """Warning: The[ \w]*import of .+ is redundant.*""".r
}