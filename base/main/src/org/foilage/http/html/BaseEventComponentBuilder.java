package org.foilage.http.html;

public abstract class BaseEventComponentBuilder<B,T> extends BaseComponentBuilder<B,T> implements ComponentBuilder<B> {

    protected String onClick = null;

    protected String onDoubleClick = null;

    protected String onDrag = null;

    protected String onDragEnd = null;

    protected String onDragEnter = null;

    protected String onDragLeave = null;

    protected String onDragOver = null;

    protected String onDragStart = null;

    protected String onDrop = null;

    protected String onMouseDown = null;

    protected String onMouseMove = null;

    protected String onMouseOut = null;

    protected String onMouseOver = null;

    protected String onMouseUp = null;

    protected String onScroll = null;

    protected String onWheel = null;

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents(){

        StringBuilder html = new StringBuilder();

        html.append(super.addGeneralComponents());

        if(onClick != null){
            html.append(" onclick=\"");
            html.append(onClick);
            html.append("\"");
        }

        if(onDoubleClick != null){
            html.append(" ondblclick=\"");
            html.append(onDoubleClick);
            html.append("\"");
        }

        if(onDrag != null){
            html.append(" ondrag=\"");
            html.append(onDrag);
            html.append("\"");
        }

        if(onDragEnd != null){
            html.append(" ondragend=\"");
            html.append(onDragEnd);
            html.append("\"");
        }

        if(onDragEnter != null){
            html.append(" ondragenter=\"");
            html.append(onDragEnter);
            html.append("\"");
        }

        if(onDragLeave != null){
            html.append(" ondragleave=\"");
            html.append(onDragLeave);
            html.append("\"");
        }

        if(onDragOver != null){
            html.append(" ondragover=\"");
            html.append(onDragOver);
            html.append("\"");
        }

        if(onDragStart != null){
            html.append(" ondragstart=\"");
            html.append(onDragStart);
            html.append("\"");
        }

        if(onDrop != null){
            html.append(" ondrop=\"");
            html.append(onDrop);
            html.append("\"");
        }

        if(onMouseDown != null){
            html.append(" onmousedown=\"");
            html.append(onMouseDown);
            html.append("\"");
        }

        if(onMouseMove != null){
            html.append(" onmousemove=\"");
            html.append(onMouseMove);
            html.append("\"");
        }

        if(onMouseOut != null){
            html.append(" onmouseout=\"");
            html.append(onMouseOut);
            html.append("\"");
        }

        if(onMouseOver != null){
            html.append(" onmouseover=\"");
            html.append(onMouseOver);
            html.append("\"");
        }

        if(onMouseUp != null){
            html.append(" onmouseup=\"");
            html.append(onMouseUp);
            html.append("\"");
        }

        if(onScroll != null){
            html.append(" onscroll=\"");
            html.append(onScroll);
            html.append("\"");
        }

        if(onWheel != null){
            html.append(" onwheel=\"");
            html.append(onWheel);
            html.append("\"");
        }

        return html.toString();
    }

    public T onClick(String onClickEvent){
        this.onClick = onClickEvent;
        return (T)this;
    }

    public T onDoubleClick(String onDoubleClickEvent){
        this.onDoubleClick = onDoubleClickEvent;
        return (T)this;
    }

    public T onDrag(String onDragEvent){
        this.onDrag = onDragEvent;
        return (T)this;
    }

    public T onDragEnd(String onDragEndEvent){
        this.onDragEnd = onDragEndEvent;
        return (T)this;
    }

    public T onDragEnter(String onDragEnterEvent){
        this.onDragEnter = onDragEnterEvent;
        return (T)this;
    }

    public T onDragLeave(String onDragLeaveEvent){
        this.onDragLeave = onDragLeaveEvent;
        return (T)this;
    }

    public T onDragOver(String onDragOverEvent){
        this.onDragOver = onDragOverEvent;
        return (T)this;
    }

    public T onDragStart(String onDragStartEvent){
        this.onDragStart = onDragStartEvent;
        return (T)this;
    }

    public T onDrop(String onDropEvent){
        this.onDrop = onDropEvent;
        return (T)this;
    }

    public T onMouseDown(String onMouseDownEvent){
        this.onMouseDown = onMouseDownEvent;
        return (T)this;
    }

    public T onMouseMove(String onMouseMoveEvent){
        this.onMouseMove = onMouseMoveEvent;
        return (T)this;
    }

    public T onMouseOut(String onMouseOutEvent){
        this.onMouseOut = onMouseOutEvent;
        return (T)this;
    }

    public T onMouseOver(String onMouseOverEvent){
        this.onMouseOver = onMouseOverEvent;
        return (T)this;
    }

    public T onMouseUp(String onMouseUpEvent){
        this.onMouseUp = onMouseUpEvent;
        return (T)this;
    }

    public T onScroll(String onScrollEvent){
        this.onScroll = onScrollEvent;
        return (T)this;
    }

    public T onWheel(String onWheelEvent){
        this.onWheel = onWheelEvent;
        return (T)this;
    }
}
