package org.sireum.hamr.vision.treetable

import java.awt._
import java.awt.event.MouseEvent
import java.util.EventObject
import javax.swing._
import javax.swing.event._
import javax.swing.table._
import javax.swing.tree._
import org.sireum._
import org.sireum.hamr.vision.value.Value
import scala.collection.mutable

class JTreeTable(treeTableModel: TreeTableModel, map: mutable.HashMap[String, JPort]) extends JTable { // Create the tree. It will be used as a renderer and editor.
  /** A subclass of JTree. */
  protected var tree: TreeTableCellRenderer = _
  tree = new TreeTableCellRenderer(treeTableModel)
  // Install a tableModel representing the visible rows in the tree.
  super.setModel(new TreeTableModelAdapter(treeTableModel, tree))
  // Force the JTable and JTree to share their row selection models.
  val selectionWrapper = new ListToTreeSelectionModelWrapper
  tree.setSelectionModel(selectionWrapper)
  setSelectionModel(selectionWrapper.getListSelectionModel)
  // Install the tree editor renderer and editor.
  setDefaultRenderer(classOf[TreeTableModel], tree)
  setDefaultEditor(classOf[TreeTableModel], new TreeTableCellEditor)
  // No grid.
  setShowGrid(false)
  // No intercell spacing
  setIntercellSpacing(new Dimension(0, 0))
  // And update the height of the trees row to match that of
  // the table.
  if (tree.getRowHeight < 1) {
    // Metal looks better like this.
    setRowHeight(18)
  }
  this.getTableHeader.setReorderingAllowed(false)
  var colorToggle = true
  def getColorToggle: Boolean = { colorToggle }
  def setColorToggle(v: Boolean): Unit = { colorToggle = v }

  var colorChoice = Color.yellow
  def setColorChoice(c: Color): Unit = { colorChoice = c}

  // the update method codegen, et other clients, will call
  def update(rowId: String, values : ISZ[Option[Value]]): Unit = {
    for(i <- 0 until values.size) {
      if (values(i).nonEmpty) {
        // want to update the ith cell of the row corresponding to
        val contents: Value = values(i).get
        val port = map(rowId)
        port.setValueAt(i, contents)
      }
    }
  }

  class rowColor extends DefaultTableCellRenderer {
    override def getTableCellRendererComponent(table: JTable, value: AnyRef, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
      val c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
      val node = table.getValueAt(row, 0)
      if (node.isInstanceOf[JPort] && getColorToggle) {
        val port = node.asInstanceOf[JPort]
        for(i <- 0 until port.updatedCells.size) {
          setBackground(if (port.updatedCells(i) && i == column) colorChoice
          else Color.white)
        }
      } else setBackground(table.getBackground)
      if (isSelected) setBackground(table.getSelectionBackground)

      table.getModel.asInstanceOf[AbstractTableModel].fireTableRowsUpdated(row, row)

      c
    }
  }

  /**
       * Overridden to message super and forward the method to the tree.
       * Since the tree is not actually in the component hierarchy it will
       * never receive this unless we forward it in this manner.
       */
  override def updateUI(): Unit = {
    super.updateUI()
    if (tree != null) tree.updateUI()
    // Use the tree's default foreground and background colors in the
    // table.
    LookAndFeel.installColorsAndFont(this, "Tree.background", "Tree.foreground", "Tree.font")
  }

  /* Workaround for BasicTableUI anomaly. Make sure the UI never tries to
       * paint the editor. The UI currently uses different techniques to
       * paint the renderers and editors and overriding setBounds() below
       * is not the right thing to do for an editor. Returning -1 for the
       * editing row in this case, ensures the editor is never painted.
       */
  override def getEditingRow: Int = if (getColumnClass(editingColumn) eq classOf[TreeTableModel]) -1
  else editingRow

  /**
       * Overridden to pass the new rowHeight to the tree.
       */
  override def setRowHeight(rowHeight: Int): Unit = {
    super.setRowHeight(rowHeight)
    if (tree != null && tree.getRowHeight != rowHeight) tree.setRowHeight(getRowHeight)
  }

  /**
       * Returns the tree that is being shared between the model.
       */
  def getTree: JTree = tree

  /**
       * A TreeCellRenderer that displays a JTree.
       */
  class TreeTableCellRenderer(model: TreeModel) extends JTree(model) with TableCellRenderer {

    /** Last table/tree row asked to renderer. */
    protected var visibleRow = 0

    /**
             * updateUI is overridden to set the colors of the Tree's renderer
             * to match that of the table.
             */
    override def updateUI(): Unit = {
      super.updateUI()
      // Make the tree's cell renderer use the table's cell selection
      // colors.
      val tcr = getCellRenderer
      if (tcr.isInstanceOf[DefaultTreeCellRenderer]) {
        val dtcr = tcr.asInstanceOf[DefaultTreeCellRenderer]
        // For 1.1 uncomment this, 1.2 has a bug that will cause an
        // exception to be thrown if the border selection color is
        // null.
        // dtcr.setBorderSelectionColor(null);
        // Colors the word
        dtcr.setTextSelectionColor(UIManager.getColor("Table.selectionForeground"))
        dtcr.setBackgroundSelectionColor(UIManager.getColor("Table.selectionBackground"))
      }
    }

    /**
             * Sets the row height of the tree, and forwards the row height to
             * the table.
             */
    override def setRowHeight(rowHeight: Int): Unit = {
      if (rowHeight > 0) {
        super.setRowHeight(rowHeight)
        if (JTreeTable.this != null && JTreeTable.this.getRowHeight != rowHeight) JTreeTable.this.setRowHeight(getRowHeight)
      }
    }

    /**
             * This is overridden to set the height to match that of the lele.
             */
    override def setBounds(x: Int, y: Int, w: Int, h: Int): Unit = {
      super.setBounds(x, 18, w, JTreeTable.this.getHeight)
    }

    /**
             * Sublcassed to translate the graphics such that the last visible
             * row will be drawn at 0,0.
             */
    override def paint(g: Graphics): Unit = {
      g.translate(0, -visibleRow * getRowHeight)
      try {
        super.paint(g)
      } catch {
        case e: Throwable => System.out.println(e)
      }
    }

    /**
             * TreeCellRenderer method. Overridden to update the visible row.
             */
    override def getTableCellRendererComponent(table: JTable, value: AnyRef, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
      // Colors the background
      for(i <- 1 until table.getColumnCount){
        table.getColumnModel.getColumn(i).setCellRenderer(new rowColor) //Overriding the cell renderers any column besides the first
      }
      if (isSelected) setBackground(table.getSelectionBackground)
      else setBackground(table.getBackground)
      visibleRow = row
      this
    }
  }

  /**
       * TreeTableCellEditor implementation. Component returned is the
       * JTree.
       */
  class TreeTableCellEditor extends AbstractCellEditor with TableCellEditor {
    override def getTableCellEditorComponent(table: JTable, value: AnyRef, isSelected: Boolean, r: Int, c: Int): Component = tree

    /**
             * Overridden to return false, and if the event is a mouse event
             * it is forwarded to the tree.<p>
             * The behavior for this is debatable, and should really be offered
             * as a property. By returning false, all keyboard actions are
             * implemented in terms of the table. By returning true, the
             * tree would get a chance to do something with the keyboard
             * events. For the most part this is ok. But for certain keys,
             * such as left/right, the tree will expand/collapse where as
             * the table focus should really move to a different column. Page
             * up/down should also be implemented in terms of the table.
             * By returning false this also has the added benefit that clicking
             * outside of the bounds of the tree node, but still in the tree
             * column will select the row, whereas if this returned true
             * that wouldn't be the case.
             * <p>By returning false we are also enforcing the policy that
             * the tree will never be editable (at least by a key sequence).
             */
    override def isCellEditable(e: EventObject): Boolean = {
      if (e.isInstanceOf[MouseEvent]) {
        for (counter <- getColumnCount - 1 to 0 by -1) {
          if (getColumnClass(counter) eq classOf[TreeTableModel]) {
            val me = e.asInstanceOf[MouseEvent]
            tree.setToggleClickCount(0)
            if (me.getClickCount == 2) {
              val row = tree.getClosestRowForLocation(me.getX, me.getY)
              val path = tree.getClosestPathForLocation(me.getX, me.getY)
              val node = path.getLastPathComponent
              var childNode = 0
              if (tree.isCollapsed(row) && node.isInstanceOf["System"]) {
                var i = 0
                while (i < tree.getRowCount) {
                  tree.expandRow(i)

                  i += 1
                }
              } else if (tree.isExpanded(row) && node.isInstanceOf["System"]) {
                var i = 0
                while (i < tree.getRowCount) {
                  tree.collapseRow(i)

                  i += 1
                }
              } else if (tree.isCollapsed(row) && node.isInstanceOf[JCategory]) {
                tree.expandRow(row)
                for (i <- 0 until treeTableModel.getChildCount(node)) {
                  if(i == 0){
                    tree.expandRow(row + 1)
                  } else {
                    childNode = treeTableModel.getChildCount(treeTableModel.getChild(node, i))
                    tree.expandRow(row + childNode + 2)
                  }
                }
              } else if(tree.isExpanded(row) && node.isInstanceOf[JCategory]) {
                for (i <- 0 until treeTableModel.getChildCount(node)) {
                  if (i == 0) {
                    tree.collapseRow(row + 1)
                  } else {
                    childNode = treeTableModel.getChildCount(treeTableModel.getChild(node, i))
                    tree.collapseRow(row + childNode + 2)
                  }
                }
                tree.collapseRow(row)
              } else {
                val n = node.asInstanceOf[JPort]
                new Info(n)
              }
            }
            val newME = new MouseEvent(tree, me.getID, me.getWhen, me.getModifiers, me.getX - getCellRect(0, counter, true).x, me.getY, me.getClickCount, me.isPopupTrigger)
            tree.dispatchEvent(newME)
          }
        }
      }
      false
    }

    override def getCellEditorValue: AnyRef = null
  }

  /**
       * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel
       * to listen for changes in the ListSelectionModel it maintains. Once
       * a change in the ListSelectionModel happens, the paths are updated
       * in the DefaultTreeSelectionModel.
       */
  class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel {
    getListSelectionModel.addListSelectionListener(createListSelectionListener)

    /** Set to true when we are updating the ListSelectionModel. */
    protected var updatingListSelectionModel = false

    /**
             * Returns the list selection model. ListToTreeSelectionModelWrapper
             * listens for changes to this model and updates the selected paths
             * accordingly.
             */
    def getListSelectionModel = listSelectionModel

    /**
             * This is overridden to set <code>updatingListSelectionModel</code>
             * and message super. This is the only place DefaultTreeSelectionModel
             * alters the ListSelectionModel.
             */
    override def resetRowSelection(): Unit = {
      if (!updatingListSelectionModel) {
        updatingListSelectionModel = true
        try super.resetRowSelection()
        finally updatingListSelectionModel = false
      }
      // Notice how we don't message super if
      // updatingListSelectionModel is true. If
      // updatingListSelectionModel is true, it implies the
      // ListSelectionModel has already been updated and the
      // paths are the only thing that needs to be updated.
    }

    /**
             * Creates and returns an instance of ListSelectionHandler.
             */
    protected def createListSelectionListener = new ListSelectionHandler()

    /**
             * If <code>updatingListSelectionModel</code> is false, this will
             * reset the selected paths from the selected rows in the list
             * selection model.
             */
    protected def updateSelectedPathsFromSelectedRows(): Unit = {
      if (!updatingListSelectionModel) {
        updatingListSelectionModel = true
        try {
          // This is way expensive, ListSelectionModel needs an
          // enumerator for iterating.
          val min = listSelectionModel.getMinSelectionIndex
          val max = listSelectionModel.getMaxSelectionIndex
          clearSelection()
          if (min != -1 && max != -1) for (counter <- min to max) {
            if (listSelectionModel.isSelectedIndex(counter)) {
              val selPath = tree.getPathForRow(counter)
              if (selPath != null) addSelectionPath(selPath)
            }
          }
        } finally updatingListSelectionModel = false
      }
    }

    /**
             * Class responsible for calling updateSelectedPathsFromSelectedRows
             * when the selection of the list changse.
             */
    class ListSelectionHandler extends ListSelectionListener {
      override def valueChanged(e: ListSelectionEvent): Unit = {
        updateSelectedPathsFromSelectedRows()
      }
    }
  }
}
