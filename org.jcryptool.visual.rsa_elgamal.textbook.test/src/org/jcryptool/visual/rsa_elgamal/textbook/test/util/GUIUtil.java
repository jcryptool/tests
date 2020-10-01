package org.jcryptool.visual.rsa_elgamal.textbook.test.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.jcryptool.mvc.view.tree.SWTTree;
import org.jcryptool.visual.rsa_elgamal.textbook.stepstone.RSA_App;

import com.diffplug.common.swt.Coat;
import com.diffplug.common.swt.Layouts;
import com.diffplug.common.swt.Shells;
import com.diffplug.common.swt.SwtMisc;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import fj.P1;
import fj.data.Stream;
import fj.data.Tree;

public class GUIUtil {

	public static void registerKeydownListener(Control control, Predicate<KeyEvent> predicate, fj.function.Effect1<KeyEvent> action) {
		registerKeydownListener(control, c -> true, predicate, action);
	}
	public static void registerKeydownListener(Control control, Predicate<Control> whichControls, Predicate<KeyEvent> predicate, fj.function.Effect1<KeyEvent> action) {
		for (Control subControl: SWTTree.of(control)) {
			subControl.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
// 					System.out.println(String.format("Receiver keyPressed %s | %s", control, e));
					if (predicate.test(e)) {
// 						System.out.println(String.format("Receiver test passed %s | %s", control, e));
						e.doit = true;
						action.f(e);
					}
				}
			});
		}
	}
	
	public static Coat defaultTestshellCoat(Coat wrapped) {
		return new Coat() {
			public void putOn(org.eclipse.swt.widgets.Composite cmp) {
				Layouts.setGrid(cmp);
				wrapped.putOn(cmp);

				// register CTRL+c to close the shell
				registerKeydownListener(cmp, 
						keyCombinationTest((char) 3, ImmutableList.of(SWT.CONTROL)),  // 0x03 is Control-c
						event -> cmp.getShell().close()
						);
				
				registerKeydownListener(cmp,
						keyCombinationTest('s', ImmutableList.of(SWT.ALT)),
						event -> resizeShell(cmp.getShell(), -10, 0));
				registerKeydownListener(cmp,
						keyCombinationTest('f', ImmutableList.of(SWT.ALT)),
						event -> resizeShell(cmp.getShell(), +10, 0));
				registerKeydownListener(cmp,
						keyCombinationTest('e', ImmutableList.of(SWT.ALT)),
						event -> resizeShell(cmp.getShell(), 000, -10));
				registerKeydownListener(cmp,
						keyCombinationTest('d', ImmutableList.of(SWT.ALT)),
						event -> resizeShell(cmp.getShell(), 000, +10));
				
// 				cmp.getShell().pack();
			};
		};
	};
	
	protected static void resizeShell(Shell shell, int i, int j) {
		int width = shell.getSize().x + i;
		int height = shell.getSize().y + j;
		if (width > 20 && height > 40) {
			shell.setSize(width, height);
		}
	}
	protected static Predicate<KeyEvent> keyCombinationTest(char character, ImmutableList<Integer> modifiers) {
		return new Predicate<KeyEvent>() {
			@Override
			public boolean test(KeyEvent e) {
				boolean flagsApply = modifiers.stream()
						.map(mod -> SwtMisc.flagIsSet(mod, e.stateMask))
						.reduce(true, Boolean::logicalAnd);
// 				System.out.println(String.format("flags=%s, %s", flagsApply, e));
				if (e.character == character && flagsApply) {
					return true;
				}
				return false;
			}
		};
	}
	
	public static void showShell(Coat coat) {
		Shells.builder(SWT.BORDER | SWT.CLOSE | SWT.RESIZE, defaultTestshellCoat(coat))
			.setSize(400, 120)
			.openOnActiveBlocking();
	}
	
}
